package com.example.inscripciones

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import com.example.inscripciones.Contract.Inscripcion_Contract
import com.example.inscripciones.Presenter.Cls_InscripcionesPresenter
import android.widget.*


class MainActivity : AppCompatActivity(), Inscripcion_Contract.View {
    private lateinit var presenter: Inscripcion_Contract.Presenter

    fun setPresentador(presentador: Inscripcion_Contract.Presenter){
        this.presenter=presentador
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        presenter = Cls_InscripcionesPresenter(this)

        // Referencias a los elementos del layout
        val edtMatricula = findViewById<EditText>(R.id.edtMatricula)
        val edtNombre = findViewById<EditText>(R.id.edtNombre)
        val edtPromedio = findViewById<EditText>(R.id.edtPromedio)
        val spinnerCarrera = findViewById<Spinner>(R.id.spnSelect)
        val btnIr = findViewById<Button>(R.id.btnIr)

        // Configurar opciones del Spinner
        val carreras = listOf(
            "Licenciatura en Gestion de Negocios",
            "Licenciatura en Contaduria",
            "Ingenieria en Procesos Alimenticios ",
            "Ingenieria en Desarrollo y Gestion de Software Multiplataforma",
            "Ingeniera en Mecatronica",
            "Ingenieria Civil",
            "Ingenieria en Metal Mecanica",
            "Otra"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, carreras)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCarrera.adapter = adapter

        // Acción al presionar el botón
        btnIr.setOnClickListener {
            val matricula = edtMatricula.text.toString()
            val nombre = edtNombre.text.toString()
            val promedio = edtPromedio.text.toString()
            val carrera = spinnerCarrera.selectedItem.toString()

            presenter.onSendClicked(matricula, nombre, promedio, carrera)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun Resultado(nombre: String, carrera: String, promedio: Double) {
        val intent = Intent(this, Resultado::class.java).apply {
            putExtra("EXTRA_NAME", nombre)
            putExtra("EXTRA_CAREER", carrera)
            putExtra("EXTRA_PROMEDIO", promedio)
        }
        startActivity(intent)
    }
}