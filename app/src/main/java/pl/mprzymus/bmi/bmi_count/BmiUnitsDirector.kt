package pl.mprzymus.bmi.bmi_count

import android.widget.TextView
import pl.mprzymus.bmi.history.database.model.HeightUnit
import pl.mprzymus.bmi.history.database.model.WeightUnit
import pl.mprzymus.bmi.validators.EnglishValidator
import pl.mprzymus.bmi.validators.MetricValidator
import pl.mprzymus.bmi.validators.UnitValidator
import java.io.Serializable

class BmiUnitsDirector(val heightUnitsStrings: List<String>, val weightUnitsStrings: List<String>) :
    Serializable {
    private val calculators = listOf(BmiMetric(), BmiEnglishUnits())
    private val validators: List<UnitValidator> = listOf(MetricValidator(), EnglishValidator())
    var index: Int = 0

    fun countBmi(height: Double, weight: Double): Double {
        return calculators[index].countBmi(height, weight)
    }

    fun switchUnits(heightTV: TextView, weightTv: TextView) {
        index = (index + 1) % calculators.size
        heightTV.text = heightUnitsStrings[index]
        weightTv.text = weightUnitsStrings[index]
    }

    fun getCurrentValidator(): UnitValidator {
        return validators[index]
    }

    fun getCurrentHeightUnit(): HeightUnit {
        val units = heightUnitsStrings[index]
        return when {
            units.contains(HeightUnit.CENTIMETER.toString()) -> HeightUnit.CENTIMETER
            units.contains(HeightUnit.INCH.toString()) -> HeightUnit.INCH
            else -> HeightUnit.UNKNOWN
        }
    }

    fun getCurrentWeightUnit(): WeightUnit {
        val units = weightUnitsStrings[index]
        return when {
            units.contains(WeightUnit.KILOGRAM.toString()) -> WeightUnit.KILOGRAM
            units.contains(WeightUnit.POUND.toString()) -> WeightUnit.POUND
            else -> WeightUnit.UNKNOWN
        }
    }
}