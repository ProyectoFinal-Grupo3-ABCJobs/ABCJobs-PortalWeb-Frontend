package com.example.proyectoabckotlin.desempenoEmpleado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroDesempenoEmpleadoBinding
import com.example.proyectoabckotlin.pojo.Contrato
import com.example.proyectoabckotlin.service.ApiDesempenoEmpleado
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


class RegistroDesempenoEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroDesempenoEmpleadoBinding
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var contratos: List<Contrato>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroDesempenoEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiDesempenoEmpleado = getRetrofit().create(ApiDesempenoEmpleado::class.java)
        val secretKey = Keys.hmacShaKeyFor("frase-secreta-token-2023-frase-secreta-token-2023-backend-abcjobs".toByteArray())
        val token = obtenerTokenDesdeSharedPreferences(this@RegistroDesempenoEmpleadoActivity)


        searchView = findViewById(R.id.searchView)
        listView = findViewById(R.id.listView)

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
            val call: Call<List<Contrato>> = apiDesempenoEmpleado.obtenerEmpleados(
                idEmpCanFunc.toString(),
                "Bearer $token"
            )
            call.enqueue(object : Callback<List<Contrato>> {
                override fun onResponse(call: Call<List<Contrato>>, response: Response<List<Contrato>>) {
                    if (response.isSuccessful) {
                        val contratos: List<Contrato>? = response.body()

                        if (contratos != null) {
                            // Inicializa el adaptador
                            adapter = ArrayAdapter(
                                this@RegistroDesempenoEmpleadoActivity,
                                android.R.layout.simple_list_item_1,
                                contratos.map { it.nombreCandidato }
                            )
                            listView.adapter = adapter

                            // Configura el listener para el SearchView
                            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                                override fun onQueryTextSubmit(query: String?): Boolean {
                                    return false
                                }

                                override fun onQueryTextChange(newText: String?): Boolean {
                                    // Filtra la lista en base a la entrada de búsqueda
                                    adapter.filter.filter(newText)
                                    return false
                                }
                            })

                            listView.setOnItemClickListener { _, _, position, _ ->
                                // Obtiene el contrato seleccionado
                                val contratoSeleccionado = contratos?.get(position)

                                // Abre el formulario pasando el contrato seleccionado
                                if (contratoSeleccionado != null) {
                                    abrirFormulario(contratoSeleccionado)
                                }
                            }
                        } else {
                            // Manejar el caso en que la lista de contratos sea nula
                        }
                    } else {
                        // Manejar la respuesta no exitosa aquí
                    }
                }

                override fun onFailure(call: Call<List<Contrato>>, t: Throwable) {
                    // Manejar el fallo de la llamada aquí
                }
            })
        }
    }

    private fun abrirFormulario(contrato: Contrato) {
        // Abre la actividad del formulario y pasa el contrato seleccionado
        val intent = Intent(this@RegistroDesempenoEmpleadoActivity, RegistroDatDesempenoEmpleadoActivity::class.java)
        intent.putExtra("idCandidato", contrato.idCandidato)
        startActivity(intent)
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