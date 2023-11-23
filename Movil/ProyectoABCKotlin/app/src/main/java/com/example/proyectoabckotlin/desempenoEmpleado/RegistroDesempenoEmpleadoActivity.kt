package com.example.proyectoabckotlin.desempenoEmpleado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.widget.SearchView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityIngresarBinding
import com.example.proyectoabckotlin.databinding.ActivityRegistroDesempenoEmpleadoBinding


class RegistroDesempenoEmpleadoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroDesempenoEmpleadoBinding
    private lateinit var searchView: SearchView
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroDesempenoEmpleadoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Inicializa las vistas
        searchView = findViewById(R.id.searchView)
        listView = findViewById(R.id.listView)

        // Datos de ejemplo
        val data = arrayOf("Apple", "Banana", "Cherry", "Date", "Grapes", "Kiwi", "Orange", "Peach", "Pear")

        // Inicializa el adaptador
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
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
    }
}