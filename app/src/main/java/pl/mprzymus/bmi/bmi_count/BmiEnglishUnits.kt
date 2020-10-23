package pl.mprzymus.bmi.bmi_count

class BmiEnglishUnits: Bmi {
    override fun countBmi(height: Double, weight: Double): Double {
        return weight / (height * height) * 703.0
    }
}