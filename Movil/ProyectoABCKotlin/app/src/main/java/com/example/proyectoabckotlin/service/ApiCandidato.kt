package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.Candidato
import com.example.proyectoabckotlin.pojo.DesempenoEmpleado
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiCandidato {
    @POST("/candidate/registerInfo")
    fun register(@Body candidato: Candidato,
                 @Header("Authorization") authorization: String
    ): Call<Candidato>

}