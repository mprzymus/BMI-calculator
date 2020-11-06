package pl.mprzymus.bmi.history.model

enum class WeightUnit(private val unitName: String) {
    KILOGRAM("kg"), POUND("lb"),
    UNKNOWN("unknown unit");

    override fun toString(): String {
        return unitName
    }

    companion object {
        fun getUnit(unit: String): WeightUnit {
            return when (unit) {
                KILOGRAM.unitName -> KILOGRAM
                POUND.unitName -> POUND
                else -> UNKNOWN
            }
        }
    }
}