package com.example.proyectoabckotlin.registroCandidato

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroResultadoPruebaBinding
import com.example.proyectoabckotlin.databinding.ActivityVerEntrevistasProgramadasBinding
import com.example.proyectoabckotlin.databinding.ActivityVerResultadosEntrevistasBinding
import com.example.proyectoabckotlin.pojo.Contrato
import com.example.proyectoabckotlin.pojo.Entrevista
import com.example.proyectoabckotlin.pojo.Prueba
import com.example.proyectoabckotlin.service.ApiEntrevista
import com.example.proyectoabckotlin.service.ApiPrueba
import com.example.proyectoabckotlin.service.HeaderInterceptor
import com.example.proyectoabckotlin.utilidades.PreferencesUtil.obtenerTokenDesdeSharedPreferences
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections



class VerEntrevistasProgramadasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerEntrevistasProgramadasBinding
    private var pruebas: List<Prueba>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerEntrevistasProgramadasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiEntrevista = getRetrofit().create(ApiEntrevista::class.java)
        val secretKey = Keys.hmacShaKeyFor("frase-secreta-token-2023-frase-secreta-token-2023-backend-abcjobs".toByteArray())
        val token = obtenerTokenDesdeSharedPreferences(this@VerEntrevistasProgramadasActivity)

        val decodedToken = Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .setAllowedClockSkewSeconds(60) // Permitir una diferencia de hasta 60 segundos
            .build()
            .parseClaimsJws(token)


        val claims = decodedToken.body
        println("Decoded Token: ${decodedToken.body}")
        val subClaim = claims["sub"] as Map<String, Any>


        val idEmpCanFunc = subClaim["idEmpCanFunc"]
        if (idEmpCanFunc != null) {
            val call: Call<List<Entrevista>> = apiEntrevista.obtenerEntrevistasCandidato(
                idEmpCanFunc.toString(),
                "Bearer $token"
            )
            call.enqueue(object : Callback<List<Entrevista>> {
                override fun onResponse(
                    call: Call<List<Entrevista>>,
                    response: Response<List<Entrevista>>
                ) {
                    if (response.isSuccessful) {
                        val entrevistas: List<Entrevista>? = response.body()
                        val cardContainer: LinearLayout = findViewById(R.id.cardContainer)
                        cardContainer.removeAllViews()

                        if (entrevistas != null) {
                            val mensajeTextView: TextView = findViewById(R.id.mensajeTextView)
                            mensajeTextView.visibility = View.GONE

                            // Iterar sobre la lista de pruebas y crear tarjetas
                            entrevistas.forEach { entrevista ->
                                if (entrevista.estado == false) {
                                    // Crear una instancia de CardView
                                    val cardViewLayout = layoutInflater.inflate(
                                        R.layout.cardview_layout_entrevista_program,
                                        null
                                    ) as CardView

                                    val layoutParams = LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT
                                    )


                                    val marginInPixels = 12
                                    layoutParams.setMargins(
                                        marginInPixels,
                                        marginInPixels,
                                        marginInPixels,
                                        marginInPixels
                                    )


                                    cardViewLayout.layoutParams = layoutParams
                                    // Obtener las vistas del layout inflado
                                    val nombreEmpresaTextView: AppCompatTextView =
                                        cardViewLayout.findViewById(R.id.nombreEmpresaTextView)
                                    val fechaTextView: AppCompatTextView =
                                        cardViewLayout.findViewById(R.id.fechaTextView)
                                    val horaTextView: AppCompatTextView =
                                        cardViewLayout.findViewById(R.id.horaTextView)

                                    // Establecer datos en las vistas
                                    nombreEmpresaTextView.text = "${entrevista.empresaNombre ?: ""}"
                                    fechaTextView.text =
                                        "Fecha: ${entrevista.fechaEntrevista ?: ""}"
                                    horaTextView.text =
                                        "Hora: ${entrevista.horaEntrevista ?: ""}"

                                    val nuevoColor = Color.parseColor("#FFFFFF")
                                    cardViewLayout.setCardBackgroundColor(nuevoColor)

                                    // Agregar la tarjeta al contenedor
                                    cardContainer.addView(cardViewLayout)
                                }
                            }


                        } else {
                            val mensajeTextView: TextView = findViewById(R.id.mensajeTextView)
                            mensajeTextView.visibility = View.VISIBLE
                        }
                    } else {
                        // Manejar la respuesta no exitosa aquí
                    }
                }

                override fun onFailure(call: Call<List<Entrevista>>, t: Throwable) {
                    // Manejar el fallo de la llamada aquí
                }
            })

        }
        try{
            binding.regresarDesempenoButton.setOnClickListener {
                finish()
            }
        } catch (ex: Exception) {
            Toast.makeText(
                this@VerEntrevistasProgramadasActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }

    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5003/")
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