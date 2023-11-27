package com.example.proyectoabckotlin.service

import com.example.proyectoabckotlin.pojo.Contrato
import com.example.proyectoabckotlin.pojo.DesempenoEmpleado
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiDesempenoEmpleado {
    @POST("/company/contrato/{idContrato}/desempenoEmpleado")
    fun register(@Body desempenoEmpleado: DesempenoEmpleado,
                 @Path("idContrato") idContrato: String,
                 @Header("Authorization") authorization: String
    ): Call<DesempenoEmpleado>

    @GET("/company/{idEmpresa}/contratos")
    fun obtenerEmpleados(
        @Path("idEmpresa") idEmpresa: String,
        @Header("Authorization") authorization: String
    ): Call<List<Contrato>>

}