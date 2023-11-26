package com.example.proyectoabckotlin.pruebas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityConsultaPruebasTecnicasBinding
import com.example.proyectoabckotlin.pruebas.adapter.PruebaAdapter
import com.example.proyectoabckotlin.pruebas.recyclerview.PruebaProvider

class ConsultaPruebasTecnicasActivity : AppCompatActivity(){

    private lateinit var binding: ActivityConsultaPruebasTecnicasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            //PruebaProvider.pruebaList
            binding = ActivityConsultaPruebasTecnicasBinding.inflate(layoutInflater)
            setContentView(binding.root)
            initRecyclerView()
        } catch (ex: Exception) {
            System.out.println(ex.toString());
        }
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_pruebas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PruebaAdapter(PruebaProvider.pruebaList)
    }
}