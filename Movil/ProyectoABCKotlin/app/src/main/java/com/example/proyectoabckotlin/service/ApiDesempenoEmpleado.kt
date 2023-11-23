package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.DesempenoEmpleado
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiDesempenoEmpleado {
    @POST("/company/desempenoEmpleado")
    fun register(@Body desempenoEmpleado: DesempenoEmpleado): Call<DesempenoEmpleado>
}