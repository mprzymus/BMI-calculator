package pl.mprzymus.bmi.bmi_count

import java.io.Serializable

interface Bmi : Serializable {
    fun countBmi(height: Double, weight: Double): Double
}