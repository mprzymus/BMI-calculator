package pl.mprzymus.bmi.bmi_count

class BmiMetric : Bmi {
    override fun countBmi(height: Double, weight: Double): Double {
        val heightCm = height * 100
        return weight / (heightCm * heightCm)
    }
}