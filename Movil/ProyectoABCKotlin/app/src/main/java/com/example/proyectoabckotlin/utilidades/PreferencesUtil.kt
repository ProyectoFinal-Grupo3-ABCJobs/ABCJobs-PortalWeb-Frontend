package com.example.proyectoabckotlin.utilidades

import android.content.Context
import android.content.SharedPreferences

object PreferencesUtil {
    // Guardar el token en SharedPreferences
    fun guardarTokenEnSharedPreferences(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    // Obtener el token desde SharedPreferences
    fun obtenerTokenDesdeSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("token", null)
    }
}