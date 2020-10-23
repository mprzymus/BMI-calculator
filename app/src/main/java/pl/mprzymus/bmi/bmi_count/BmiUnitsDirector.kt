package pl.mprzymus.bmi.bmi_count

import android.widget.TextView

class BmiUnitsDirector(private val heightUnits: List<String>, private val weightUnits: List<String>) {
    private val calculators = listOf(BmiMetric(), BmiEnglishUnits())
    var index: Int = 0

    fun countBmi(height: Double, weight: Double): Double {
        return calculators[index].countBmi(height, weight)
    }

    fun switchUnits(heightTV: TextView, weightTv: TextView) {
        index = (index + 1) % calculators.size
        heightTV.text = heightUnits[index]
        weightTv.text = weightUnits[index]
    }
}