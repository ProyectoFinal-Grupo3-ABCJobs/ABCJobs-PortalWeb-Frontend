package com.example.proyectoabckotlin.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityMainBinding
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.Usuario
import com.example.proyectoabckotlin.registroCandidato.CandidatoMainActivity
import com.example.proyectoabckotlin.registroCandidato.RegistroCandidatoActivity
import com.example.proyectoabckotlin.service.ApiAutenticacion
import com.example.proyectoabckotlin.service.HeaderInterceptor
import com.example.proyectoabckotlin.utilidades.PreferencesUtil
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys


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
                    val editUsuarioText = edit_usuario.text.toString().trim { it <= ' ' }
                    val editContrasenaText = edit_contrasena.text.toString().trim { it <= ' ' }
                    Log.e("MainActivity", "edit_usuario_text$editUsuarioText")
                    println("edit_usuario_text: $editUsuarioText")
                    if (editUsuarioText.isEmpty()) {
                        edit_usuario.error = getString(R.string.valida_usuario)
                        return
                    }
                    if (editContrasenaText.isEmpty()) {
                        edit_contrasena.error = getString(R.string.valida_contrasena)
                        return
                    }
                    val apiAutenticacion = getRetrofit().create(ApiAutenticacion::class.java)
                    val usuarioBody = Usuario()
                    usuarioBody.usuario = editUsuarioText
                    usuarioBody.contrasena = editContrasenaText

                    val secretKey = Keys.hmacShaKeyFor("frase-secreta-token-2023-frase-secreta-token-2023-backend-abcjobs".toByteArray())
                    val call = apiAutenticacion.login(usuarioBody)

                    call.enqueue(object : Callback<Usuario?> {
                        override fun onResponse(
                            call: Call<Usuario?>,
                            response: Response<Usuario?>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                edit_usuario.text.clear()
                                edit_contrasena.text.clear()
                                val usuario = response.body()!!
                                val token = usuario.token

                                if (token != null) {
                                    PreferencesUtil.guardarTokenEnSharedPreferences(this@MainActivity, token)
                                }

                                val decodedToken = Jwts.parserBuilder()
                                    .setSigningKey(secretKey)
                                    .setAllowedClockSkewSeconds(60) // Permitir una diferencia de hasta 60 segundos
                                    .build()
                                    .parseClaimsJws(token)


                                val claims = decodedToken.body
                                println("Decoded Token: ${decodedToken.body}")
                                val subClaim = claims["sub"] as Map<String, Any>

                                val idEmpCanFunc = subClaim["idEmpCanFunc"]

                                println("idEmpCanFunc: ${idEmpCanFunc}")

                                //Llamar el Activity de Registro de Candidato

                                Toast.makeText(
                                    this@MainActivity,
                                    getString(R.string.login_exitoso),
                                    Toast.LENGTH_LONG
                                ).show()
                                if(response.body()!!.tipoUsuario=="CANDIDATO")
                                    startActivity(Intent(this@MainActivity, CandidatoMainActivity::class.java))
                                else
                                    startActivity(Intent(this@MainActivity, IngresarActivity::class.java))
                            } else {
                                Toast.makeText(
                                    this@MainActivity,
                                    getString(R.string.datos_incorrectos),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                            println("onFailure")
                            Toast.makeText(
                                this@MainActivity,
                                getString(R.string.error_ingresando),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            })

            binding.registrarCandidatoLoginButton.setOnClickListener {
                startActivity(Intent(this@MainActivity, RegistroCandidatoActivity::class.java))
            }
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
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.getClient())
            .build()
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