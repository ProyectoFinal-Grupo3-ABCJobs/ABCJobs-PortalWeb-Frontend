package com.example.proyectoabckotlin.service;

import com.example.proyectoabckotlin.pojo.Usuario
import retrofit2.Call;
import retrofit2.http.Body
import retrofit2.http.POST;

public interface ApiAutenticacion {
    /*@GET("/login/{id}")
    fun login(@Path("id") id: String): Call<Usuario>

    @GET("/login?usuario={usuario}")
    fun login(@Query("usuario") usuario: String): Call<Usuario>*/

    @POST("/users/auth")
    fun login(@Body usuario: Usuario): Call<Usuario>
}

