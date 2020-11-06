package pl.mprzymus.bmi.history.model

import java.io.Serializable

class BmiRecordData(
    val weight: Double,
    val weightUnit: WeightUnit,
    val height: Double,
    val heightUnit: HeightUnit,
    val bmi: Double
): Serializable {
    override fun toString(): String {
        return String.format(
            "weight: %.1f%s, height: %.1f%s, bmi: %.1f",
            weight,
            weightUnit,
            height,
            heightUnit,
            bmi
        )
    }


}