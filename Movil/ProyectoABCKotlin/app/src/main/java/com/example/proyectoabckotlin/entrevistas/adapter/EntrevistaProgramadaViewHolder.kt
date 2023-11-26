package com.example.proyectoabckotlin.pruebas.adapter

import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.entrevistas.recyclerview.EntrevistaProgramada

class EntrevistaProgramadaViewHolder (view: View): RecyclerView.ViewHolder(view){

    val text_fecha = view.findViewById<TextView>(R.id.text_view_fecha)
    val text_empresa = view.findViewById<TextView>(R.id.text_view_empresa)
    val text_hora = view.findViewById<TextView>(R.id.text_view_hora)

    fun render(entrevistaProgramadaModel: EntrevistaProgramada){
        text_fecha.text = entrevistaProgramadaModel.fecha;
        text_empresa.text = entrevistaProgramadaModel.empresa;
        text_hora.text = entrevistaProgramadaModel.hora;
    }

}