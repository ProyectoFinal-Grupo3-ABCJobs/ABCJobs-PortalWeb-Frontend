package com.example.proyectoabckotlin.registroCandidato

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoabckotlin.databinding.ActivityCandidatoMainBinding


class CandidatoMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCandidatoMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCandidatoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {

            binding.resultadoPruebasButton.setOnClickListener(object : View.OnClickListener {

                override fun onClick(view: View) {
                    startActivity(Intent(this@CandidatoMainActivity, VerResultadoPruebaActivity::class.java))
                }

            })
            binding.resultadoEntrevistasButton.setOnClickListener(object : View.OnClickListener {

                override fun onClick(view: View) {
                    startActivity(Intent(this@CandidatoMainActivity, VerResultadosEntrevistasActivity::class.java))
                }

            })

            binding.entrevistasProgramadasButton.setOnClickListener {
                startActivity(Intent(this@CandidatoMainActivity,VerEntrevistasProgramadasActivity::class.java))
            }

            binding.regresarButton.setOnClickListener {
                finish()
            }

        } catch (ex: Exception) {
            Toast.makeText(
                this@CandidatoMainActivity,
                ex.toString(),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}