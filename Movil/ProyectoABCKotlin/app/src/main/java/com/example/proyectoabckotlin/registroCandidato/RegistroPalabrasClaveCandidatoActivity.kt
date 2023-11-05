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
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoEduCandidatoBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroPalabrasClaveCandidatoBinding
import com.example.proyectoabckotlin.service.HeaderInterceptor
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Collections

class RegistroPalabrasClaveCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroPalabrasClaveCandidatoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroPalabrasClaveCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        try {
            val intentUsuario = intent
            val usuario = intentUsuario.getStringExtra("usuario")
            val contrasena = intentUsuario.getStringExtra("contrasena")
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
            val anioGrado = intentUsuario.getStringExtra("anioGrado")
            val cargoUltimoEmpleo = intentUsuario.getStringExtra("cargoUltimoEmpleo")
            val ultimaEmpresa = intentUsuario.getStringExtra("ultimaEmpresa")
            val anioIngreso = intentUsuario.getStringExtra("anioIngreso")

            binding.guardarInfoCandidatoButton .setOnClickListener(object : View.OnClickListener {
                var registro_palabras_claves = findViewById<EditText>(R.id.palabraClaveEditText)

                override fun onClick(view: View) {
                    val registropalabrasClaveText = registro_palabras_claves.text.toString().trim { it <= ' ' }

                    if (registropalabrasClaveText.isEmpty()) {
                        registro_palabras_claves.error = getString(R.string.valida_claves)
                        return
                    }

                    val intentUsuario = intent
                    val usuarioRecibido = intentUsuario.getStringExtra("usuario")
                    val contrasenaRecibida = intentUsuario.getStringExtra("contrasena")
                    val tipoIdentificacion = intentUsuario.getStringExtra("tipoIdentificacion")
                    val identificacionRecibida = intentUsuario.getStringExtra("identificacion")
                    val telefonoRecibido = intentUsuario.getStringExtra("telefono")
                    val direccionRecibida = intentUsuario.getStringExtra("direccion")
                    val paisRecibido = intentUsuario.getStringExtra("pais")
                    val departamentoRecibido = intentUsuario.getStringExtra("departamento")
                    val ciudadRecibido = intentUsuario.getStringExtra("ciudad")
                    val profesionRecibida = intentUsuario.getStringExtra("profesion")
                    val experienciaRecibida = intentUsuario.getStringExtra("experiencia")

                    if (usuarioRecibido != null && contrasenaRecibida != null) {
                        Log.d("usuario", usuarioRecibido)
                        Log.d("contrasena", contrasenaRecibida)
                    }



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





