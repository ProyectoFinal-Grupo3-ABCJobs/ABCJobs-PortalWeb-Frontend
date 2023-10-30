package com.example.proyectoabckotlin.registroCandidato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroCandidatoBinding
<<<<<<< Updated upstream
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.Usuario
import com.example.proyectoabckotlin.service.ApiAutenticacion
=======
>>>>>>> Stashed changes
import com.example.proyectoabckotlin.service.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections

class RegistroCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroCandidatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
<<<<<<< Updated upstream
            binding.continuarRegistroButton.setOnClickListener {
                var edit_usuario = findViewById<EditText>(R.id.usuario_registro_edit)
                var edit_contrasena = findViewById<EditText>(R.id.contrasena_registro_edit)
                /*intent = Intent(this@RegistroCandidatoActivity, Actividad Info Candidato::class.java))
                intent.putExtra("usuario", edit_usuario.text.toString().trim { it <= ' ' })
                intent.putExtra("contrasena", edit_usuario.text.toString().trim { it <= ' ' })
                startActivity(intent)*/
            }
=======
            binding.continuarRegistroButton.setOnClickListener(object : View.OnClickListener {
                var registro_usuario = findViewById<EditText>(R.id.usuario_registro_edit)
                var registro_contrasena = findViewById<EditText>(R.id.contrasena_registro_edit)
                var repetir_contrasena =
                    findViewById<EditText>(R.id.repetir_contrasena_registro_edit)

                override fun onClick(view: View) {
                    val registroUsuarioText = registro_usuario.text.toString().trim { it <= ' ' }
                    val registroContrasenaText =
                        registro_contrasena.text.toString().trim { it <= ' ' }
                    val registroRepetirContrasenaText =
                        repetir_contrasena.text.toString().trim { it <= ' ' }
                    Log.e("MainActivity", "edit_usuario_text$registroUsuarioText")
                    println("edit_usuario_text: $registroUsuarioText")
                    if (registroUsuarioText.isEmpty()) {
                        registro_usuario.error = getString(R.string.valida_usuario)
                        return
                    }
                    if (registroContrasenaText.isEmpty()) {
                        registro_contrasena.error = getString(R.string.valida_contrasena)
                        return
                    }
                    if (registroRepetirContrasenaText.isEmpty()) {
                        repetir_contrasena.error = getString(R.string.valida_contrasena)
                        return
                    }
                    if (registroContrasenaText != registroRepetirContrasenaText) {
                        repetir_contrasena.error = getString(R.string.valida_igual_contrasena)
                        return
                    }

                    intent = Intent(
                        this@RegistroCandidatoActivity,
                        RegistroInfoCandidatoActivity::class.java
                    )
                    intent.putExtra("usuario", registro_usuario.text.toString().trim { it <= ' ' })
                    intent.putExtra(
                        "contrasena",
                        registro_contrasena.text.toString().trim { it <= ' ' })
                    startActivity(intent)
                }
            })
>>>>>>> Stashed changes

            binding.regresarCandidatoLoginButton.setOnClickListener {
                finish()
            }

        } catch (ex: Exception) {
            Toast.makeText(
                this@RegistroCandidatoActivity,
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