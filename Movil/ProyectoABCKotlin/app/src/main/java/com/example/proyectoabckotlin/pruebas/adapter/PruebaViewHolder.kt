package com.example.proyectoabckotlin.pruebas.adapter

import android.view.View
import android.widget.TableLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoabckotlin.R
import com.example.proyectoabckotlin.pruebas.recyclerview.Prueba

class PruebaViewHolder (view: View): RecyclerView.ViewHolder(view){

    val text_empresa = view.findViewById<TextView>(R.id.text_view_empresa)
    val table_layout = view.findViewById<TableLayout>(R.id.table_layout_prueba)
    /*val text_prueba = view.findViewById<TextView>(R.id.table_text_prueba)
    val text_resultados = view.findViewById<TextView>(R.id.table_text_resultado)
    val text_observaciones = view.findViewById<TextView>(R.id.table_text_observaciones)*/
    fun render(entrevistaProgramadaModel: Prueba){
        text_empresa.text = entrevistaProgramadaModel.empresa;
        /*text_prueba.text = pruebaModel.pruebas[0];
        text_resultados.text = pruebaModel.pruebas[1];
        text_observaciones.text = pruebaModel.pruebas[2];*/

        /*table_layout.removeAllViews()*/

        /*for (elemento in pruebaModel.pruebas) {
            val tableRow = TableRow(itemView.context)

            // Configurar celdas según tus necesidades
            val textViewElemento = TextView(itemView.context)
            textViewElemento.text = elemento
            tableRow.addView(textViewElemento)
            table_layout.addView(tableRow)
        }*/

    }

}