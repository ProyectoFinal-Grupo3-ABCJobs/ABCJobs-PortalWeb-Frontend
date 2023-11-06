package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiUsuario {
    @POST("/users/register")
    fun register(@Body usuario: Usuario): Call<Usuario>
}