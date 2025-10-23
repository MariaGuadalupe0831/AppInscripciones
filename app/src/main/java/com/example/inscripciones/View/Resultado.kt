package com.example.inscripciones.View

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.inscripciones.Model.Cls_InscripcionesModel
import com.example.inscripciones.R
import java.util.Locale

class Resultado : AppCompatActivity() {

    private val model = Cls_InscripcionesModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        // Referencias a los TextView del layout
        val tvNombre = findViewById<TextView>(R.id.txtNombre)
        val tvCarrera = findViewById<TextView>(R.id.tvCarrera)
        val tvPromedio = findViewById<TextView>(R.id.txtPromedio)
        val tvMontoBase = findViewById<TextView>(R.id.txtMontoBase)
        val tvDescuento = findViewById<TextView>(R.id.txtDescuento)
        val tvMontoFinal = findViewById<TextView>(R.id.txtTotal)

        // Recuperar parámetros enviados por Intent
        val nombre = intent.getStringExtra("EXTRA_NAME") ?: "--"
        val carrera = intent.getStringExtra("EXTRA_CAREER") ?: "--"
        val promedio = intent.getDoubleExtra("EXTRA_PROMEDIO", -1.0)

        // Calcular descuento y monto final
        val (descuento, total) = model.calcularMontoFinal(promedio)

        // Mostrar los resultados
        tvNombre.text = "Nombre: $nombre"
        tvCarrera.text = "Carrera: $carrera"
        tvPromedio.text = String.Companion.format(Locale.US, "Promedio: %.2f", promedio)
        tvMontoBase.text = String.Companion.format(Locale.US, "Monto base: %.2f", Cls_InscripcionesModel.Companion.MontoBase)

        val rate = model.calcularDescuento(promedio)
        tvDescuento.text = String.Companion.format(Locale.US, "Descuento (%.0f%%): -%.2f", rate * 100, descuento)
        tvMontoFinal.text = String.Companion.format(Locale.US, "Monto final a pagar: %.2f", total)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}