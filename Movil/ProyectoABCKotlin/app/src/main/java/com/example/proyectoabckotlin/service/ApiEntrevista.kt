package com.example.proyectoabckotlin.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.proyectoabckotlin.pojo.Entrevista
import com.example.proyectoabckotlin.pojo.Prueba
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiEntrevista {
    @GET("/test/candidate/{idCandidato}/interviews")
    fun obtenerEntrevistasCandidato(
        @Path("idCandidato") idCandidato: String,
        @Header("Authorization") authorization: String
    ): Call<List<Entrevista>>
}