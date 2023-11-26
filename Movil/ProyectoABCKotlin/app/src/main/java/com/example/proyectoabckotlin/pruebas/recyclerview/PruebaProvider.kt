package com.example.proyectoabckotlin.pruebas.recyclerview

class PruebaProvider {
    companion object{
        val pruebaList = listOf<Prueba>(
            Prueba("Empresa1", listOf("Tecnica","90/100","Excelente","Habilidades Blandas","10/100","Bueno")),
            Prueba("Empresa2", listOf("Habilidades Blandas","10/100","Bueno")),
            Prueba("Empresa3", listOf("Responsabilidad","85/100","Gran")),
        )
    }
}