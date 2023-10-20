package com.example.proyectoabckotlin.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityMainBinding
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.Usuario
import com.example.proyectoabckotlin.registroCandidato.RegistroCandidatoActivity
import com.example.proyectoabckotlin.service.ApiAutenticacion
import com.example.proyectoabckotlin.service.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            binding.ingresarLoginButton.setOnClickListener(object : View.OnClickListener {
                var edit_usuario = findViewById<EditText>(R.id.usuario_login_edit)
                var edit_contrasena = findViewById<EditText>(R.id.contrasena_login_edit)
                override fun onClick(view: View) {
                    val edit_usuario_text = edit_usuario.text.toString().trim { it <= ' ' }
                    val edit_contrasena_text = edit_contrasena.text.toString().trim { it <= ' ' }
                    Log.e("MainActivity", "edit_usuario_text" + edit_usuario_text)
                    println("edit_usuario_text: " + edit_usuario_text)
                    if (edit_usuario_text.isNullOrEmpty() || edit_usuario_text.isNullOrEmpty()) {
                        Toast.makeText(
                            this@MainActivity,
                            "Debe ingresar usuario y contraseña",
                            Toast.LENGTH_LONG
                        ).show()
                        return
                    }
                    val apiAutenticacion = getRetrofit().create(ApiAutenticacion::class.java)
                    var usuario_body = Usuario()
                    usuario_body.usuario = edit_usuario_text
                    usuario_body.contrasena = edit_contrasena_text
                    val call = apiAutenticacion.login(usuario_body)

                    call!!.enqueue(object : Callback<Usuario?> {
                        override fun onResponse(
                            call: Call<Usuario?>,
                            response: Response<Usuario?>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                edit_usuario.text.clear()
                                edit_contrasena.text.clear()
                                //String token = response.body().getToken();
                                //Llamar el Activity de Registro de Candidato
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login exitoso",
                                    Toast.LENGTH_LONG
                                ).show()
                                val intent =
                                    Intent(this@MainActivity, IngresarActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Datos incorrectos",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                            println("onFailure")
                            Toast.makeText(
                                this@MainActivity,
                                "Error ingresando, intente nuevamente",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            })

            binding.registrarCandidatoLoginButton.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View) {
                    val intent =
                        Intent(this@MainActivity, RegistroCandidatoActivity::class.java)
                    startActivity(intent)

                }
            })
        } catch (ex: Exception) {
            Toast.makeText(
                this@MainActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://bca0-190-26-23-62.ngrok-free.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.getClient())
            .build();
    }

    private fun getClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .protocols(Collections.singletonList(Protocol.HTTP_1_1))
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()
    }
}