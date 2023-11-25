package com.example.proyectoabckotlin.ingresar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.databinding.ActivityIngresarBinding
import com.example.proyectoabckotlin.desempenoEmpleado.RegistroDesempenoEmpleadoActivity


class IngresarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIngresarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIngresarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            binding.evaluacionDesempenoButton.setOnClickListener {
                startActivity(Intent(this@IngresarActivity, RegistroDesempenoEmpleadoActivity::class.java))
            }

            binding.regresarButton.setOnClickListener {
                finish()
            }

        } catch (ex: Exception) {
            Toast.makeText(
                this@IngresarActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}