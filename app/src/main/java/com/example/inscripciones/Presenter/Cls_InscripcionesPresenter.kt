package com.example.inscripciones.Presenter
import com.example.inscripciones.Contract.Inscripcion_Contract
import com.example.inscripciones.Model.Cls_InscripcionesModel

class Cls_InscripcionesPresenter(private val view: Inscripcion_Contract.View) : Inscripcion_Contract.Presenter {
    private val model = Cls_InscripcionesModel()
    override fun onSendClicked(
        matricula: String,
        nombre: String,
        promedioStr: String,
        carrera: String
    ) {
        if (nombre.isBlank()) {
            view.showError("El nombre es obligatorio")
            return
        }


        val promedio = promedioStr.replace(',', '.').toDoubleOrNull()
        if (promedio == null) {
            view.showError("Introduce un promedio válido")
            return
        }

        view.Resultado(nombre.trim(), carrera, promedio)
    }
}