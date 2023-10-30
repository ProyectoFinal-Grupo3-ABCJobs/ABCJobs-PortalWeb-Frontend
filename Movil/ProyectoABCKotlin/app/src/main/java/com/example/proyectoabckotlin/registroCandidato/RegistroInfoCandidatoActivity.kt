package com.example.proyectoabckotlin.registroCandidato

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityMainBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoCandidatoBinding
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

class RegistroInfoCandidatoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroInfoCandidatoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroInfoCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            /*binding.continuarRegisPerCandidatoButton.setOnClickListener*/

                val intent = intent
                if (intent != null) {
                    val textoRecibido = intent.getStringExtra("usuario")
                }

                val tipoIdentificacionSpinner = findViewById<Spinner>(R.id.tipoIdentificacionSpinner)
                val paisSpinner = findViewById<Spinner>(R.id.paisSpinner)
                val departamentoSpinner = findViewById<Spinner>(R.id.departamentoSpinner)
                val ciudadSpinner = findViewById<Spinner>(R.id.ciudadSpinner)

            // Obtén el arreglo de tipos de identificación desde los recursos
                val tiposIdentificacionArray = resources.getStringArray(R.array.tipos_identificacion)
                val paisArray = resources.getStringArray(R.array.pais)
                val departamentosArray = resources.getStringArray(R.array.departamento)
                val ciudadArray = resources.getStringArray(R.array.ciudad)

            // Configura un adaptador para el Spinner
                val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposIdentificacionArray)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                tipoIdentificacionSpinner.adapter = adapter

                // Maneja la selección de elementos del Spinner
                tipoIdentificacionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val tipoSeleccionado = tiposIdentificacionArray[position]
                        // Puedes hacer algo con el tipo de identificación seleccionado
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // No se seleccionó nada
                    }
                }

            val adapterPais = ArrayAdapter(this, android.R.layout.simple_spinner_item, paisArray)
            adapterPais.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            paisSpinner.adapter = adapterPais

            // Maneja la selección de elementos del Spinner
            paisSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val tipoSeleccionado = paisArray[position]
                    // Puedes hacer algo con el tipo de identificación seleccionado
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            val adapterDepartamento = ArrayAdapter(this, android.R.layout.simple_spinner_item, departamentosArray)
            adapterDepartamento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            departamentoSpinner.adapter = adapterDepartamento

            // Maneja la selección de elementos del Spinner
            departamentoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val tipoSeleccionado = departamentosArray[position]
                    // Puedes hacer algo con el tipo de identificación seleccionado
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            val adapterCiudad = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudadArray)
            adapterCiudad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            ciudadSpinner.adapter = adapterCiudad

            // Maneja la selección de elementos del Spinner
            ciudadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val tipoSeleccionado = ciudadArray[position]
                    // Puedes hacer algo con el tipo de identificación seleccionado
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }


                /*var edit_usuario = findViewById<EditText>(R.id.usuario_registro_edit)
                var edit_contrasena = findViewById<EditText>(R.id.contrasena_registro_edit)
                intent = Intent(this@RegistroInfoCandidatoActivity, RegistroInfoCandidatoActivity::class.java)
                intent.putExtra("usuario", edit_usuario.text.toString().trim { it <= ' ' })
                intent.putExtra("edit_contrasena", edit_contrasena.text.toString().trim { it <= ' ' })
                startActivity(intent)*/


        } catch (ex: Exception) {
            Toast.makeText(
                this@RegistroInfoCandidatoActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://LoadBalancerProyectoABC-735612126.us-east-2.elb.amazonaws.com:5001/")
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