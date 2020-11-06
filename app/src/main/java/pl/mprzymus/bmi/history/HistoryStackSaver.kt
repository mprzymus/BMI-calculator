package pl.mprzymus.bmi.history

import android.app.Activity
import android.content.Context
import android.util.Log
import pl.mprzymus.bmi.history.model.BmiRecordData
import pl.mprzymus.bmi.history.model.HeightUnit
import pl.mprzymus.bmi.history.model.WeightUnit

class HistoryStackSaver(private val activity: Activity) {
    companion object {
        const val DATA_SIZE = "size"
        const val WEIGHT = "weight"
        const val WEIGHT_UNIT = "weightUnit"
        const val HEIGHT = "height"
        const val HEIGHT_UNIT = "heightUnit"
        const val BMI = "bmi"
    }

    fun save(data: FixedStack<BmiRecordData>) {
        val size = data.size
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(DATA_SIZE, size)
            apply()
        }
        repeat(size) {
            print(it)
            with(sharedPref.edit()) {
                putFloat(WEIGHT + it, data[it].weight.toFloat())
                putString(WEIGHT_UNIT + it, data[it].weightUnit.toString())
                putFloat(HEIGHT + it, data[it].height.toFloat())
                putString(HEIGHT_UNIT + it, data[it].heightUnit.toString())
                putFloat(BMI + it, data[it].bmi.toFloat())
                apply()
            }
        }
    }
    fun load(): FixedStack<BmiRecordData> {
        val stack = FixedStack<BmiRecordData>(HistoryActivity.MAX_HISTORY_SIZE)
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val size = sharedPref.getInt(DATA_SIZE, 0)
        repeat(size) {
            print(it)
            val weight = sharedPref.getFloat(WEIGHT + it, 0F).toDouble()
            val weightUnitName = sharedPref.getString(WEIGHT_UNIT + it, "unknown")
            val height = sharedPref.getFloat(HEIGHT + it, 0F).toDouble()
            val heightUnitName = sharedPref.getString(HEIGHT_UNIT + it, "unknown")
            val bmi = sharedPref.getFloat(BMI + it, 0F).toDouble()
            if (weight != 0.0 && weight != 0.0 && bmi != 0.0 && weightUnitName != null && heightUnitName != null) {
                Log.d("loading", "works for $it")
                val weightUnit = WeightUnit.getUnit(weightUnitName)
                val heightUnit = HeightUnit.getUnit(heightUnitName)
                stack.push(BmiRecordData(weight, weightUnit, height, heightUnit, bmi))
            }

        }
        return stack
    }
}