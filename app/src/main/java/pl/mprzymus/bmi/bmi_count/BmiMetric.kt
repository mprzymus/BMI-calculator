package pl.mprzymus.bmi.bmi_count

class BmiMetric : Bmi {
    override fun countBmi(height: Double, weight: Double): Double {
        if (height < 0.0 || weight < 0.0) {
            throw IllegalArgumentException("Negative values in input: height: $height weight: $weight")
        }
        val heightMeters = height / 100
        return weight / (heightMeters * heightMeters)
    }
}