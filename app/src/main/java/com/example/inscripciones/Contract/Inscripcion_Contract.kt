package com.example.inscripciones.Contract

interface Inscripcion_Contract {

    interface View {
        fun showError(message: String)
        fun Resultado(nombre: String, carrera: String, promedio: Double)
    }
    interface Presenter {
        fun onSendClicked(matricula: String, nombre: String, promedioStr: String, carrera: String)
    }
}