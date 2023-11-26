package com.example.proyectoabckotlin.pruebas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.pruebas.recyclerview.Prueba

class PruebaAdapter(private val pruebaList:List<Prueba>) : RecyclerView.Adapter<PruebaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PruebaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PruebaViewHolder(layoutInflater.inflate(R.layout.item_prueba, parent, false))
    }

    override fun onBindViewHolder(holder: PruebaViewHolder, position: Int) {
        if(pruebaList != null && !pruebaList.isEmpty()) {
            val item = pruebaList[position]
            holder.render(item)
        }
    }

    override fun getItemCount(): Int = pruebaList.size
}