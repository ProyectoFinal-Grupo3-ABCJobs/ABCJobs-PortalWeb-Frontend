package com.example.proyectoabckotlin.ingresar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityIngresarBinding
import com.example.proyectoabckotlin.desempenoEmpleado.RegistroDesempenoEmpleadoActivity
import com.example.proyectoabckotlin.prueba.RegistroResultadoPruebaActivity
import com.example.proyectoabckotlin.registroCandidato.RegistroCandidatoActivity
import com.example.proyectoabckotlin.service.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections


class IngresarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngresarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            binding.resultadoPruebasButton.setOnClickListener(object : View.OnClickListener {

                override fun onClick(view: View) {
                    startActivity(Intent(this@IngresarActivity, RegistroResultadoPruebaActivity::class.java))
                }

            })

            binding.evaluacionDesempenoButton.setOnClickListener {
                startActivity(Intent(this@IngresarActivity, RegistroDesempenoEmpleadoActivity::class.java))
            }
        } catch (ex: Exception) {
            Toast.makeText(
                this@IngresarActivity,
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