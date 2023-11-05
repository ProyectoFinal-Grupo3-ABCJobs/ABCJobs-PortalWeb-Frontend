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
    private var idDepartamentoSeleccionado: Int = 0
    private var idCiudadSeleccionado: Int = 0

    data class Departamento(val id: Int, val nombre: String)
    data class Ciudad(val id: Int, val nombre: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroInfoEduCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val listaDepartamentos = listOf(
                RegistroInfoEduCandidatoActivity.Departamento(0, "Departamento"),
                RegistroInfoEduCandidatoActivity.Departamento(1, "Antioquia"),
                RegistroInfoEduCandidatoActivity.Departamento(2, "Cundinamarca"),
                RegistroInfoEduCandidatoActivity.Departamento(3, "Valle del cauca"),
                // Agrega más países según sea necesario
            )

            val listaCiudades = listOf(
                RegistroInfoEduCandidatoActivity.Ciudad(0, "Ciudad"),
                RegistroInfoEduCandidatoActivity.Ciudad(1, "Medellin"),
                RegistroInfoEduCandidatoActivity.Ciudad(2, "Bogota"),
                RegistroInfoEduCandidatoActivity.Ciudad(3, "Cali"),
                // Agrega más países según sea necesario
            )

            class departamentoAdapter(context: Context, val listaDepartamentos: List<RegistroInfoEduCandidatoActivity.Departamento>) : ArrayAdapter<RegistroInfoEduCandidatoActivity.Departamento>(context, android.R.layout.simple_spinner_item, listaDepartamentos) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaDepartamentos[position].nombre
                    return view
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaDepartamentos[position].nombre
                    return view
                }
            }
            val spinnerDep = findViewById<Spinner>(R.id.departamentoSpinner)
            val adapterDep= departamentoAdapter(this, listaDepartamentos)
            spinnerDep.adapter = adapterDep

            val defaultValueDep = "Departamento"
            val defaultPositionDep = listaDepartamentos.indexOfFirst { it.nombre == defaultValueDep }

            if (defaultPositionDep >= 0) {
                spinnerDep.setSelection(defaultPositionDep)
            }

            // Maneja la selección de elementos del Spinner
            spinnerDep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val departamentoSeleccionado = listaDepartamentos[position]
                    if (position > 0) {
                        val idDepartamentoSeleccionado = departamentoSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoEduCandidatoActivity, "Departamento seleccionado: $idDepartamentoSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            class ciudadAdapter(context: Context, val listaCiudades: List<RegistroInfoEduCandidatoActivity.Ciudad>) : ArrayAdapter<RegistroInfoEduCandidatoActivity.Ciudad>(context, android.R.layout.simple_spinner_item, listaCiudades) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaCiudades[position].nombre
                    return view
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaCiudades[position].nombre
                    return view
                }
            }
            val spinnerCiu = findViewById<Spinner>(R.id.ciudadSpinner)
            val adapterCiu= ciudadAdapter(this, listaCiudades)
            spinnerCiu.adapter = adapterCiu

            val defaultValueCiu = "Ciudad"
            val defaultPositionCiu = listaCiudades.indexOfFirst { it.nombre == defaultValueCiu }

            if (defaultPositionCiu >= 0) {
                spinnerDep.setSelection(defaultPositionCiu)
            }

            // Maneja la selección de elementos del Spinner
            spinnerCiu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val ciudadSeleccionado = listaCiudades[position]
                    if (position > 0) {
                        val idCiudadSeleccionado = ciudadSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoEduCandidatoActivity, "Ciudad seleccionado: $idCiudadSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            binding.continuarRegisLabCandidatoButton .setOnClickListener(object : View.OnClickListener {
                var registro_ultimoEstudio = findViewById<EditText>(R.id.ultimoEstudioEditText)
                var registro_institucion = findViewById<EditText>(R.id.institucionEditText)
                var registro_anioGrado = findViewById<EditText>(R.id.anioGradoEditText)
                var registro_cargo = findViewById<EditText>(R.id.cargoEditText)
                var registro_ultimaEmpresa = findViewById<EditText>(R.id.ultimaEmpresaEditText)
                var registro_anioIngreso = findViewById<EditText>(R.id.anioIngresoEditText)
                var registro_anioRetiro = findViewById<EditText>(R.id.anioRetiroEditText)




                override fun onClick(view: View) {
                    val registroUltimoEstudioText = registro_ultimoEstudio.text.toString().trim { it <= ' ' }
                    val registroInstitucionText = registro_institucion.text.toString().trim { it <= ' ' }
                    val registroAnioGradoText = registro_anioGrado.text.toString().trim { it <= ' ' }
                    val registroCargoText = registro_cargo.text.toString().trim { it <= ' ' }
                    val registroUltimaEmpresaText = registro_ultimaEmpresa.text.toString().trim { it <= ' ' }
                    val registroAnioIngresoText = registro_anioIngreso.text.toString().trim { it <= ' ' }
                    val registroAnioRetiroText = registro_anioRetiro.text.toString().trim { it <= ' ' }


                    if (registroUltimoEstudioText.isEmpty()) {
                        registro_ultimoEstudio.error = getString(R.string.valida_identificacion)
                        return
                    }
                    if (registroInstitucionText.isEmpty()) {
                        registro_institucion.error = getString(R.string.valida_direccion)
                        return
                    }
                    if (registroAnioGradoText.isEmpty()) {
                        registro_anioGrado.error = getString(R.string.validacion_telefono)
                        return
                    }
                    if (registroCargoText.isEmpty()) {
                        registro_cargo.error = getString(R.string.valida_experiencia)
                        return
                    }
                    if (registroUltimaEmpresaText.isEmpty()) {
                        registro_ultimaEmpresa.error = getString(R.string.valida_experiencia)
                        return
                    }
                    if (registroAnioIngresoText.isEmpty()) {
                        registro_anioIngreso.error = getString(R.string.valida_experiencia)
                        return
                    }

                    val intentUsuario = intent
                    val usuarioRecibido = intentUsuario.getStringExtra("usuario")
                    val contrasenaRecibida = intentUsuario.getStringExtra("contrasena")
                    val nombreRecibido = intentUsuario.getStringExtra("nombre")
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

                    intent = Intent(
                        this@RegistroInfoEduCandidatoActivity,
                        RegistroPalabrasClaveCandidatoActivity::class.java
                    )
                    intent.putExtra("usuario", usuarioRecibido)
                    intent.putExtra("contrasena",contrasenaRecibida)
                    intent.putExtra("nombre",nombreRecibido)
                    intent.putExtra("tipoIdentificacion",tipoIdentificacion)
                    intent.putExtra("identificacion",identificacionRecibida)
                    intent.putExtra("telefono",telefonoRecibido)
                    intent.putExtra("direccion",direccionRecibida)
                    intent.putExtra("idPais",paisRecibido)
                    intent.putExtra("idDepartamento",departamentoRecibido)
                    intent.putExtra("idCiudad",ciudadRecibido)
                    intent.putExtra("profesion",profesionRecibida)
                    intent.putExtra("experiencia",experienciaRecibida)

                    intent.putExtra("ultimoEstudio", registro_ultimoEstudio.text.toString().trim { it <= ' ' })
                    intent.putExtra("institucion",registro_institucion.text.toString().trim { it <= ' ' })
                    intent.putExtra("anioGrado", registro_anioGrado.text.toString().trim { it <= ' ' })
                    intent.putExtra("cargoUltimoEmpleo", registro_cargo.text.toString().trim { it <= ' ' })
                    intent.putExtra("ultimaEmpresa", registro_ultimaEmpresa.text.toString().trim { it <= ' ' })
                    intent.putExtra("anioIngreso",registro_anioIngreso.text.toString().trim { it <= ' ' })
                    intent.putExtra("anioRetiro",registro_anioRetiro.text.toString().trim { it <= ' ' })
                    intent.putExtra("idDepartamentoInst",idDepartamentoSeleccionado)
                    intent.putExtra("idCiudadInst",idCiudadSeleccionado)


                    startActivity(intent)
                }
            })


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





