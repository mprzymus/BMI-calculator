package pl.mprzymus.bmi.history.model

enum class HeightUnit(private val unitName: String) {
    CENTIMETER("cm"), INCH("in"), UNKNOWN("unknown unit");

    override fun toString(): String {
        return unitName
    }

    companion object {
        fun getUnit(unit: String): HeightUnit {
            return when (unit) {
                CENTIMETER.unitName -> CENTIMETER
                INCH.unitName -> INCH
                else -> UNKNOWN
            }
        }
    }

}