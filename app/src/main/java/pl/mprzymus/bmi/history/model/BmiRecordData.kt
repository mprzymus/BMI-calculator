package pl.mprzymus.bmi.history.model

import java.io.Serializable

class BmiRecordData(
    private val weight: Double,
    private val weightUnit: WeightUnit,
    private val height: Double,
    private val heightUnit: HeightUnit,
    private val bmi: Double
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