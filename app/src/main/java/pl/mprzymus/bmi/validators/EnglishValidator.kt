package pl.mprzymus.bmi.validators

import java.io.Serializable

class EnglishValidator: UnitValidator, Serializable {

    companion object {
        const val WEIGHT_MIN = 66.0
        const val WEIGHT_MAX = 1100.0
        const val HEIGHT_MIN = 47.0
        const val HEIGHT_MAX = 100.0
    }

    override fun isWeightTooLow(weight: Double): Boolean {
        return weight < WEIGHT_MIN
    }

    override fun isWeightTooHigh(weight: Double): Boolean {
        return weight > WEIGHT_MAX
    }

    override fun isHeightTooHigh(height: Double): Boolean {
        return height < HEIGHT_MIN
    }

    override fun isHeightTooLow(height: Double): Boolean {
        return height > HEIGHT_MAX
    }
}