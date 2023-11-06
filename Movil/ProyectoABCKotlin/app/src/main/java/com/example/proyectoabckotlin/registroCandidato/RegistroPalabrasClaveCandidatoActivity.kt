package com.example.proyectoabckotlin.registroCandidato

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoEduCandidatoBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroPalabrasClaveCandidatoBinding
import com.example.proyectoabckotlin.ingresar.IngresarActivity
import com.example.proyectoabckotlin.pojo.Candidato
import com.example.proyectoabckotlin.pojo.Usuario
import com.example.proyectoabckotlin.service.ApiAutenticacion
import com.example.proyectoabckotlin.service.ApiCandidato
import com.example.proyectoabckotlin.service.ApiUsuario
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

class RegistroPalabrasClaveCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroPalabrasClaveCandidatoBinding
    private lateinit var palabraClaveEditText: EditText
    private lateinit var agregarPalabraButton: Button
    private lateinit var palabrasListView: ListView
    private val palabrasList: ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroPalabrasClaveCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            palabraClaveEditText = findViewById(R.id.palabraClaveEditText)
            agregarPalabraButton = findViewById(R.id.agregarPalabraButton)
            palabrasListView = findViewById(R.id.palabrasListView)

            val adaptadorPalabras =
                ArrayAdapter(this, android.R.layout.simple_list_item_1, palabrasList)
            palabrasListView.adapter = adaptadorPalabras

            agregarPalabraButton.setOnClickListener(View.OnClickListener {
                val palabra = palabraClaveEditText.text.toString()
                if (palabra.isNotBlank()) {
                    palabrasList.add(palabra)
                    adaptadorPalabras.notifyDataSetChanged()
                    palabraClaveEditText.text.clear()
                }
            })

            binding.guardarInfoCandidatoButton.setOnClickListener(object : View.OnClickListener {
                var registro_palabras_claves = findViewById<EditText>(R.id.palabraClaveEditText)

                override fun onClick(view: View) {
                    val registropalabrasClaveText =
                        registro_palabras_claves.text.toString().trim { it <= ' ' }

                    if (palabrasList.isEmpty()) {
                        registro_palabras_claves.error = getString(R.string.valida_claves)
                        return
                    }

                    val intentUsuario = intent
                    val usuario = intentUsuario.getStringExtra("usuario")
                    val contrasena = intentUsuario.getStringExtra("contrasena")
                    val nombre = intentUsuario.getStringExtra("nombre")
                    val tipoIdentificacion = intentUsuario.getStringExtra("tipoIdentificacion")
                    val identificacion = intentUsuario.getStringExtra("identificacion")
                    val telefono = intentUsuario.getStringExtra("telefono")
                    val direccion = intentUsuario.getStringExtra("direccion")
                    val idPais = intentUsuario.getStringExtra("idPais")
                    val idDepartamento = intentUsuario.getStringExtra("idDepartamento")
                    val idCiudad = intentUsuario.getStringExtra("idCiudad")
                    val profesion = intentUsuario.getStringExtra("profesion")
                    val aniosExperiencia = intentUsuario.getStringExtra("experiencia")
                    val ultimoEstudio = intentUsuario.getStringExtra("ultimoEstudio")
                    val institucion = intentUsuario.getStringExtra("institucion")
                    val anioGrado = intentUsuario.getStringExtra("anioGrado")?.toInt()
                    val cargoUltimoEmpleo = intentUsuario.getStringExtra("cargoUltimoEmpleo")
                    val empresa = intentUsuario.getStringExtra("ultimaEmpresa")
                    val anioIngreso = intentUsuario.getStringExtra("anioIngreso")?.toInt()
                    val anioRetiro = intentUsuario.getStringExtra("anioRetiro")?.toInt()
                    val idDepartamentoInst = intentUsuario.getStringExtra("idDepartamentoInst")
                    val idCiudadInst = intentUsuario.getStringExtra("idCiudadInst")


                    if (usuario != null && contrasena != null) {
                        Log.d("usuario", usuario)
                        Log.d("contrasena", contrasena)
                    }
                    Log.d("lista de palabras", palabrasList.toString())

                    val apiUsuario = getRetrofit().create(ApiUsuario::class.java)
                    val usuarioBody = Usuario()
                    usuarioBody.usuario = usuario
                    usuarioBody.contrasena = contrasena
                    usuarioBody.tipoUsuario = "CANDIDATO"
                    val call = apiUsuario.register(usuarioBody)

                    call.enqueue(object : Callback<Usuario?> {
                        override fun onResponse(
                            call: Call<Usuario?>,
                            response: Response<Usuario?>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                Toast.makeText(
                                    this@RegistroPalabrasClaveCandidatoActivity,
                                    "Registro usuario exitoso",
                                    Toast.LENGTH_LONG
                                ).show()
                                Log.d("Response", response.toString())

                            } else {
                                Toast.makeText(
                                    this@RegistroPalabrasClaveCandidatoActivity,
                                    getString(R.string.datos_incorrectos),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<Usuario?>, t: Throwable) {
                            println("onFailure")
                            Toast.makeText(
                                this@RegistroPalabrasClaveCandidatoActivity,
                                getString(R.string.error_ingresando),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })

                    val apiCandidato = getRetrofitCandidato().create(ApiCandidato::class.java)
                    val candidatoBody = Candidato()
                    candidatoBody.nombre = nombre
                    candidatoBody.tipoIdentificacion = tipoIdentificacion
                    candidatoBody.identificacion = identificacion
                    candidatoBody.direccion = direccion
                    candidatoBody.telefono = telefono
                    candidatoBody.profesion = profesion
                    candidatoBody.aniosExperiencia = aniosExperiencia
                    candidatoBody.idCiudad = idCiudad
                    candidatoBody.idDepartamento = idDepartamento
                    candidatoBody.idPais = idPais
                    candidatoBody.ultimoEstudio = ultimoEstudio
                    candidatoBody.institucion = institucion
                    candidatoBody.anioGrado = anioGrado
                    candidatoBody.idCiudadInst = idCiudadInst
                    candidatoBody.idDepartamentoInst = idDepartamentoInst
                    candidatoBody.cargoUltimoEmpleo = cargoUltimoEmpleo
                    candidatoBody.empresa = empresa
                    candidatoBody.anioIngreso = anioIngreso
                    candidatoBody.anioRetiro = anioRetiro
                    candidatoBody.estado = false
                    candidatoBody.palabrasClave= palabrasList.toString()

                    val callCandidato = apiCandidato.register(candidatoBody)

                    callCandidato.enqueue(object : Callback<Candidato?> {
                        override fun onResponse(
                            callCandidato: Call<Candidato?>,
                            response: Response<Candidato?>
                        ) {
                            if (response.isSuccessful && response.body() != null) {

                                Toast.makeText(
                                    this@RegistroPalabrasClaveCandidatoActivity,
                                    getString(R.string.registro_candidato_exitoso),
                                    Toast.LENGTH_LONG
                                ).show()
                                startActivity(
                                    Intent(
                                        this@RegistroPalabrasClaveCandidatoActivity,
                                        IngresarActivity::class.java
                                    )
                                )
                            } else {
                                Toast.makeText(
                                    this@RegistroPalabrasClaveCandidatoActivity,
                                    getString(R.string.datos_incorrectos),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(callCandidato: Call<Candidato?>, t: Throwable) {
                            println("onFailure")
                            Toast.makeText(
                                this@RegistroPalabrasClaveCandidatoActivity,
                                getString(R.string.error_ingresando),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            })
        }
        catch (ex: Exception) {
            Toast.makeText(
                this@RegistroPalabrasClaveCandidatoActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5000")
            .addConverterFactory(GsonConverterFactory.create())
            .client(this.getClient())
            .build()
    }

    private fun getRetrofitCandidato(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5001")
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





