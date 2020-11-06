package pl.mprzymus.bmi.history.model

import java.io.Serializable

class BmiRecordData(
    val weight: Double,
    val weightUnit: WeightUnit,
    val height: Double,
    val heightUnit: HeightUnit,
    val bmi: Double,
    val date: String
): Serializable {
    fun getData(): String {
        return String.format(
            "%.1f%s, %.1f%s, BMI: %.1f",
            weight,
            weightUnit,
            height,
            heightUnit,
            bmi
        )
    }


}