package com.example.inscripciones.Model

class Cls_InscripcionesModel {
    companion object {
        const val MontoBase = 1500.0
    }


    fun calcularDescuento(promedio: Double): Double {
        return when {
            promedio >= 9.5 -> 0.40
            promedio >= 8.5 -> 0.20
            else -> 0.0
        }
    }


    fun calcularMontoFinal(promedio: Double): Pair<Double, Double> {
        val rate = calcularDescuento(promedio)
        val descuento = MontoBase * rate
        val final = MontoBase - descuento
        return Pair(descuento, final)
    }
}