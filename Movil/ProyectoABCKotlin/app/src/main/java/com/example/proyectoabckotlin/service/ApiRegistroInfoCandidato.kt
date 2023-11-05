package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.Candidato
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRegistroInfoCandidato {
    @POST("5001/candidate/registerInfo")
    fun login(@Body candidato: Candidato): Call<Candidato>
}