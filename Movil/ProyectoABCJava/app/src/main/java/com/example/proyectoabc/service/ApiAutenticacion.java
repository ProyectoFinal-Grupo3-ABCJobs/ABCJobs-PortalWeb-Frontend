package com.example.proyectoabc.service;

import com.example.proyectoabc.main.Usuario;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiAutenticacion {
    @FormUrlEncoded
    @Headers("Accept: application/json")
    @POST("login")
    Call<Usuario>LOGIN_CALL(
            @Field("usuario") String usuario,
            @Field("contrasena") String contrasena
    );
}
