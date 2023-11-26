package com.example.proyectoabckotlin.pruebas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.databinding.ActivityEntrevistasProgramadasBinding
import com.example.proyectoabckotlin.pruebas.adapter.EntrevistaProgramadaAdapter
import com.example.proyectoabckotlin.pruebas.recyclerview.EntrevistaProgramadaProvider

class EntrevistasProgramadasActivity : AppCompatActivity(){

    private lateinit var binding: ActivityEntrevistasProgramadasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            binding = ActivityEntrevistasProgramadasBinding.inflate(layoutInflater)
            setContentView(binding.root)
            initRecyclerView()
        } catch (ex: Exception) {
            System.out.println(ex.toString());
        }
    }

    fun initRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_entre_program_item)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EntrevistaProgramadaAdapter(EntrevistaProgramadaProvider.entrevistaProgramadaList)
    }
}