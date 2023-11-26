package com.example.proyectoabckotlin.pruebas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.entrevistas.recyclerview.EntrevistaProgramada

class EntrevistaProgramadaAdapter(private val entrevistaProgramadaList:List<EntrevistaProgramada>) : RecyclerView.Adapter<EntrevistaProgramadaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntrevistaProgramadaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EntrevistaProgramadaViewHolder(layoutInflater.inflate(R.layout.item_entrevistas_programadas, parent, false))
    }

    override fun onBindViewHolder(holder: EntrevistaProgramadaViewHolder, position: Int) {
        if(entrevistaProgramadaList != null && !entrevistaProgramadaList.isEmpty()) {
            val item = entrevistaProgramadaList[position]
            holder.render(item)
        }
    }

    override fun getItemCount(): Int = entrevistaProgramadaList.size
}