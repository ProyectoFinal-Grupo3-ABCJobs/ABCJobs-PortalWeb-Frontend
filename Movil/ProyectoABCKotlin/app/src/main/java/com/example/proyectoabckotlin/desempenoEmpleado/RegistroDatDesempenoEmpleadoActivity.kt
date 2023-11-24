package com.example.proyectoabckotlin.desempenoEmpleado

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroDatDesempenoEmpleadoBinding
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.DesempenoEmpleado
import com.example.proyectoabckotlin.service.ApiDesempenoEmpleado
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


class RegistroDatDesempenoEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroDatDesempenoEmpleadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroDatDesempenoEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val idContrato = intent.getIntExtra("idContrato", 0)
        val nombreCandidato = intent.getStringExtra("nombreCandidato")

        val nombreCandidatoTextView: TextView = findViewById(R.id.nombreCandidatoTextView)
        nombreCandidatoTextView.text = "$nombreCandidato"

        try {
            binding.registroDesempenoButton .setOnClickListener(object : View.OnClickListener {
            var calificacion_edit = findViewById<EditText>(R.id.calificacion_registro_edit)
            var aspectos_resaltar_edit = findViewById<EditText>(R.id.aspectosResaltar_registro_edit)
            var aspectos_mejorar_edit = findViewById<EditText>(R.id.aspectos_mejorar_registro_edit)

            override fun onClick(view: View) {
                val editCalificacionText = calificacion_edit.text.toString().trim { it <= ' ' }
                val editAspectoResaltarText = aspectos_resaltar_edit.text.toString().trim { it <= ' ' }
                val editAspectoMejorarText = aspectos_mejorar_edit.text.toString().trim { it <= ' ' }

                if (editCalificacionText.isEmpty()) {
                    calificacion_edit.error = getString(R.string.valida_calificacion)
                    return
                }
                if (editAspectoResaltarText.isEmpty()) {
                    aspectos_resaltar_edit.error = getString(R.string.valida_aspectos_resaltar)
                    return
                }

                if (editAspectoMejorarText.isEmpty()) {
                    aspectos_mejorar_edit.error = getString(R.string.valida_aspectos_mejorar)
                    return
                }

                val apiDesempenoEmpleado = getRetrofit().create(ApiDesempenoEmpleado::class.java)
                val desempenoEmpleadoBody = DesempenoEmpleado()
                desempenoEmpleadoBody.calificacion = editCalificacionText
                desempenoEmpleadoBody.aspectosResaltar = editAspectoResaltarText
                desempenoEmpleadoBody.aspectosMejorar = editAspectoMejorarText

                val secretKey = Keys.hmacShaKeyFor("frase-secreta-token-2023-frase-secreta-token-2023-backend-abcjobs".toByteArray())
                val token =
                    PreferencesUtil.obtenerTokenDesdeSharedPreferences(this@RegistroDatDesempenoEmpleadoActivity)


                val call = apiDesempenoEmpleado.register(desempenoEmpleadoBody,
                    idContrato.toString(),
                    "Bearer $token"
                )

                call.enqueue(object : Callback<DesempenoEmpleado?> {
                    override fun onResponse(
                        call: Call<DesempenoEmpleado?>,
                        response: Response<DesempenoEmpleado?>
                    ){
                        if (response.isSuccessful && response.body() != null) {
                            calificacion_edit.text.clear()
                            aspectos_resaltar_edit.text.clear()
                            aspectos_mejorar_edit.text.clear()
                            val desempenoEmpleado = response.body()!!

                            Toast.makeText(
                                this@RegistroDatDesempenoEmpleadoActivity,
                                getString(R.string.desempeno_empleado_exitoso),
                                Toast.LENGTH_LONG
                            ).show()
                            startActivity(Intent(this@RegistroDatDesempenoEmpleadoActivity, RegistroDesempenoEmpleadoActivity::class.java))
                        } else {
                            Toast.makeText(
                                this@RegistroDatDesempenoEmpleadoActivity,
                                getString(R.string.datos_incorrectos_desempeno),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<DesempenoEmpleado?>, t: Throwable) {
                        println("onFailure")
                        Toast.makeText(
                        this@RegistroDatDesempenoEmpleadoActivity,
                                getString(R.string.error_ingresando),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            })

            binding.regresarDesempenoButton.setOnClickListener {
                finish()
            }
        } catch (ex: Exception) {
            Toast.makeText(
                this@RegistroDatDesempenoEmpleadoActivity,
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

