package com.example.proyectoabckotlin.pojo

class Usuario {
    var usuario: String? = null
    var contrasena: String? = null
    var token: String? = null
    var tipoUsuario: String? = null

    fun crearUsuario(usuario:String, contrasena:String): Usuario {
        val nuevoUsuario = Usuario()
        nuevoUsuario.usuario = usuario
        nuevoUsuario.contrasena = contrasena
        return nuevoUsuario
    }
}