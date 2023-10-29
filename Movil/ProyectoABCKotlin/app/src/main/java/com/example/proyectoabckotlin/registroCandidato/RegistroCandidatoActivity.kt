package com.example.proyectoabckotlin.registroCandidato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityMainBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroCandidatoBinding
import com.example.proyectoabckotlin.registroCandidato.RegistroInfoCandidatoActivity
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.Usuario
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

class RegistroCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroCandidatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            binding.continuarRegistroButton.setOnClickListener {
                var edit_usuario = findViewById<EditText>(R.id.usuario_registro_edit)
                var edit_contrasena = findViewById<EditText>(R.id.contrasena_registro_edit)
                intent = Intent(this@RegistroCandidatoActivity, RegistroInfoCandidatoActivity::class.java)
                intent.putExtra("usuario", edit_usuario.text.toString().trim { it <= ' ' })
                intent.putExtra("edit_contrasena", edit_contrasena.text.toString().trim { it <= ' ' })
                startActivity(intent)
            }

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