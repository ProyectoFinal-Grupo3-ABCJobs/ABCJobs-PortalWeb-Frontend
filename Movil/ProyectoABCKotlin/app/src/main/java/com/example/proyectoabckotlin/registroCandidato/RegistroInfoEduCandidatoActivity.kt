package com.example.proyectoabckotlin.registroCandidato

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoCandidatoBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoEduCandidatoBinding
import com.example.proyectoabckotlin.service.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections

class RegistroInfoEduCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroInfoEduCandidatoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroInfoEduCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val intentUsuario = intent
            val usuarioRecibido = intentUsuario.getStringExtra("usuario")
            val contrasenaRecibida = intentUsuario.getStringExtra("contrasena")
            val identificacionRecibida = intentUsuario.getStringExtra("identificacion")
            val telefonoRecibido = intentUsuario.getStringExtra("telefono")
            val direccionRecibida = intentUsuario.getStringExtra("direccion")
            val profesionRecibida = intentUsuario.getStringExtra("profesion")
            val experienciaRecibida = intentUsuario.getStringExtra("experiencia")

            if (usuarioRecibido != null && contrasenaRecibida != null) {
                Log.d("usuario", usuarioRecibido)
                Log.d("contrasena", contrasenaRecibida)
                if (identificacionRecibida != null) {
                    Log.d("identificacion", identificacionRecibida)
                }
                if (telefonoRecibido != null) {
                    Log.d("telefono", telefonoRecibido)
                }
                if (direccionRecibida != null) {
                    Log.d("direccion", direccionRecibida)
                }
                if (profesionRecibida != null) {
                    Log.d("profesion", profesionRecibida)
                }
                if (experienciaRecibida != null) {
                    Log.d("experiencia", experienciaRecibida)
                }

            }

        }
        catch (ex: Exception) {
            Toast.makeText(
                this@RegistroInfoEduCandidatoActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5002/")
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





