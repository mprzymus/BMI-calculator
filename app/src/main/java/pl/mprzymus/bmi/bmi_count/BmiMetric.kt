package pl.mprzymus.bmi.bmi_count

class BmiMetric : Bmi {
    override fun countBmi(height: Double, weight: Double): Double {
        val heightMeters = height / 100
        return weight / (heightMeters * heightMeters)
    }
}