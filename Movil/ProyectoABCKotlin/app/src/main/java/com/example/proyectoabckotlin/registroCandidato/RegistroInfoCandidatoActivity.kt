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
import com.example.proyectoabckotlin.databinding.ActivityMainBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroInfoCandidatoBinding
import com.example.proyectoabckotlin.registroCandidato.RegistroInfoEduCandidatoActivity
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
    private var paisSeleccionado: String = ""
    data class Pais(val id: Int, val nombre: String)
    data class TipoIdentificacion(val id: String, val nombre: String)
    data class Departamento(val id: Int, val nombre: String)
    data class Ciudad(val id: Int, val nombre: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroInfoCandidatoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        try {
            /*binding.continuarRegisPerCandidatoButton.setOnClickListener*/

            val tipoIdentificacionSpinner = findViewById<Spinner>(R.id.tipoIdentificacionSpinner)
            val paisSpinner = findViewById<Spinner>(R.id.paisSpinner)
            val departamentoSpinner = findViewById<Spinner>(R.id.departamentoSpinner)
            val ciudadSpinner = findViewById<Spinner>(R.id.ciudadSpinner)

            //val tiposIdentificacionArray = resources.getStringArray(R.array.tipos_identificacion)

            val tiposIdentificacionArray = listOf(
                TipoIdentificacion("CC", "Cédula"),
                TipoIdentificacion("PA", "Pasaporte"),
                TipoIdentificacion("RC", "Registro Civil"),
                TipoIdentificacion("TI", "Tarjeta de Identidad"),
                // Agrega más países según sea necesario
            )

            val listaPaises = listOf(
                Pais(1, "Colombia"),
                Pais(2, "México"),
                Pais(3, "Estados Unidos"),
                // Agrega más países según sea necesario
            )

            val listaDepartamentos = listOf(
                Departamento(1, "Antioquia"),
                Departamento(2, "Cundinamarca"),
                Departamento(3, "Valle del cauca"),
                // Agrega más países según sea necesario
            )

            val listaCiudades = listOf(
                Ciudad(1, "Medellin"),
                Ciudad(2, "Bogota"),
                Ciudad(3, "Cali"),
                // Agrega más países según sea necesario
            )

            class tipoIdentificacionAdapter(context: Context, val tiposIdentificacionArray: List<TipoIdentificacion>) : ArrayAdapter<TipoIdentificacion>(context, android.R.layout.simple_spinner_item, tiposIdentificacionArray) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = tiposIdentificacionArray[position].nombre
                    return view
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = tiposIdentificacionArray[position].nombre
                    return view
                }
            }



            val spinnerTI = findViewById<Spinner>(R.id.tipoIdentificacionSpinner)
            val adapterTI = tipoIdentificacionAdapter(this, tiposIdentificacionArray)
            spinnerTI.adapter = adapterTI

            val defaultValue = "Tipo Identificación"
            val defaultPosition = tiposIdentificacionArray.indexOfFirst { it.nombre == defaultValue }

            if (defaultPosition >= 0) {
                spinnerTI.setSelection(defaultPosition)
            } else {
                Log.e("Spinner", "Valor por defecto no encontrado en tiposIdentificacionArray")
            }
            // Maneja la selección de elementos del Spinner
            spinnerTI.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val tipoIdSeleccionado = tiposIdentificacionArray[position]
                    if (position > 0) {
                        val idTipoSeleccionado = tipoIdSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoCandidatoActivity, "País seleccionado: $idTipoSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            class paisAdapter(context: Context, val listaPaises: List<Pais>) : ArrayAdapter<Pais>(context, android.R.layout.simple_spinner_item, listaPaises) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaPaises[position].nombre
                    return view
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val view = super.getDropDownView(position, convertView, parent)
                    view.findViewById<TextView>(android.R.id.text1).text = listaPaises[position].nombre
                    return view
                }
            }
            val spinnerP = findViewById<Spinner>(R.id.paisSpinner)
            val adapterP = paisAdapter(this, listaPaises)
            spinnerP.adapter = adapterP
            // Maneja la selección de elementos del Spinner
            spinnerP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val paisSeleccionado = listaPaises[position]
                    if (position > 0) {
                        val idPaisSeleccionado = paisSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoCandidatoActivity, "País seleccionado: $idPaisSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            class departamentoAdapter(context: Context, val listaDepartamentos: List<Departamento>) : ArrayAdapter<Departamento>(context, android.R.layout.simple_spinner_item, listaDepartamentos) {
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
            // Maneja la selección de elementos del Spinner
            spinnerDep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val departamentoSeleccionado = listaDepartamentos[position]
                    if (position > 0) {
                        val idDepartamentoSeleccionado = departamentoSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoCandidatoActivity, "Departamento seleccionado: $idDepartamentoSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }

            class ciudadAdapter(context: Context, val listaCiudades: List<Ciudad>) : ArrayAdapter<Ciudad>(context, android.R.layout.simple_spinner_item, listaCiudades) {
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
            // Maneja la selección de elementos del Spinner
            spinnerCiu.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val ciudadSeleccionado = listaCiudades[position]
                    if (position > 0) {
                        val idCiudadSeleccionado = ciudadSeleccionado.id
                        // El valor seleccionado no es "Selecciona un país", haz algo con él
                        // Por ejemplo, mostrarlo en un Toast
                        Toast.makeText(this@RegistroInfoCandidatoActivity, "Ciudad seleccionado: $idCiudadSeleccionado", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se seleccionó nada
                }
            }


            binding.continuarRegisPerCandidatoButton.setOnClickListener(object : View.OnClickListener {
                var registro_identificacion = findViewById<EditText>(R.id.identificacionEditText)
                var registro_direccion = findViewById<EditText>(R.id.direccionEditText)
                var registro_telefono = findViewById<EditText>(R.id.telefonoEditText)
                var registro_profesion = findViewById<EditText>(R.id.profesionEditText)
                var registro_experiencia = findViewById<EditText>(R.id.experienciaEditText)
                val spinner = findViewById<Spinner>(R.id.paisSpinner)

                // Obtiene el valor seleccionado del Spinner
                val selectedValue = spinner.selectedItem.toString()

                override fun onClick(view: View) {
                    val registroUsuarioText = registro_identificacion.text.toString().trim { it <= ' ' }
                    val registroDireccionText = registro_direccion.text.toString().trim { it <= ' ' }
                    val registroTelefonoText = registro_telefono.text.toString().trim { it <= ' ' }
                    val registroProfesionText = registro_profesion.text.toString().trim { it <= ' ' }
                    val registroExperienciaText = registro_experiencia.text.toString().trim { it <= ' ' }


                    Log.e("MainActivity", "edit_usuario_text$registroUsuarioText")
                    println("edit_usuario_text: $registroUsuarioText")

                    if (registroUsuarioText.isEmpty()) {
                        registro_identificacion.error = getString(R.string.valida_identificacion)
                        return
                    }
                    if (registroDireccionText.isEmpty()) {
                        registro_direccion.error = getString(R.string.valida_direccion)
                        return
                    }
                    if (registroTelefonoText.isEmpty()) {
                        registro_telefono.error = getString(R.string.validacion_telefono)
                        return
                    }
                    if (registroProfesionText.isEmpty()) {
                        registro_profesion.error = getString(R.string.valida_profesion)
                        return
                    }
                    if (registroExperienciaText.isEmpty()) {
                        registro_experiencia.error = getString(R.string.valida_experiencia)
                        return
                    }

                    val intentUsuario = intent
                    val usuarioRecibido = intentUsuario.getStringExtra("usuario")
                    val contrasenaRecibida = intentUsuario.getStringExtra("contrasena")

                    if (usuarioRecibido != null && contrasenaRecibida != null) {
                          Log.d("usuario", usuarioRecibido)
                          Log.d("contrasena", contrasenaRecibida)
                    }

                    Log.d("tipoIdentificacion", paisSeleccionado)

                    intent = Intent(
                        this@RegistroInfoCandidatoActivity,
                        RegistroInfoEduCandidatoActivity::class.java
                    )
                    intent.putExtra("usuario", usuarioRecibido)
                    intent.putExtra("contrasena",contrasenaRecibida)
                    intent.putExtra("identificacion", registro_identificacion.text.toString().trim { it <= ' ' })
                    intent.putExtra("direccion",registro_direccion.text.toString().trim { it <= ' ' })
                    intent.putExtra("telefono", registro_telefono.text.toString().trim { it <= ' ' })
                    intent.putExtra("profesion", registro_profesion.text.toString().trim { it <= ' ' })
                    intent.putExtra("experiencia", registro_experiencia.text.toString().trim { it <= ' ' })

                    startActivity(intent)
                }
            })

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