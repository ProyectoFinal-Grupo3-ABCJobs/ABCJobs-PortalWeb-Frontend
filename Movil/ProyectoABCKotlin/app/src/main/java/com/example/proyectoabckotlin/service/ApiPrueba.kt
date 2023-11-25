package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.Contrato
import com.example.proyectoabckotlin.pojo.Prueba
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiPrueba {
    @POST("/test/{idPrueba}")
    fun registerPrueba(@Path("idPrueba") idPrueba: String,
                       @Body prueba: Prueba,
                       @Header("Authorization") authorization: String
    ): Call<Prueba>

    @GET("/test/candidate/{idCandidato}")
    fun obtenerPruebasCandidato(
        @Path("idCandidato") idCandidato: String,
        @Header("Authorization") authorization: String
    ): Call<List<Prueba>>
}