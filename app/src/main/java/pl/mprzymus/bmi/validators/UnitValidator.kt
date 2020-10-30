package pl.mprzymus.bmi.validators

interface UnitValidator {
    fun isWeightTooLow (weight: Double): Boolean
    fun isWeightTooHigh(weight: Double): Boolean
    fun isHeightTooHigh(height: Double): Boolean
    fun isHeightTooLow (height: Double): Boolean
}