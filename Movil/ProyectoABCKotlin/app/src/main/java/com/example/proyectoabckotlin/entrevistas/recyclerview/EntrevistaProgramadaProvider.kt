package com.example.proyectoabckotlin.pruebas.recyclerview

import com.example.proyectoabckotlin.entrevistas.recyclerview.EntrevistaProgramada
import java.util.Date

class EntrevistaProgramadaProvider {
    companion object {
        val entrevistaProgramadaList = listOf<EntrevistaProgramada>(
            EntrevistaProgramada(Date().toString(), "Empresa1", Date().toString()),
            EntrevistaProgramada(Date().toString(), "Empresa2", Date().toString()),
            EntrevistaProgramada(Date().toString(), "Empresa3", Date().toString())
        )
    }
}