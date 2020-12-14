package pl.mprzymus.bmi.history

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import pl.mprzymus.bmi.history.database.model.BmiRecordData
import pl.mprzymus.bmi.history.database.model.HeightUnit
import pl.mprzymus.bmi.history.database.model.WeightUnit

class HistoryStackSaver(private val activity: Activity) {
    companion object {
        const val DATA_SIZE = "size"
        const val WEIGHT = "weight"
        const val WEIGHT_UNIT = "weightUnit"
        const val HEIGHT = "height"
        const val HEIGHT_UNIT = "heightUnit"
        const val BMI = "bmi"
        const val DATE = "date"
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
            saveRecord(sharedPref, it, data)
        }
    }

    private fun saveRecord(
        sharedPref: SharedPreferences,
        position: Int,
        data: FixedStack<BmiRecordData>
    ) {
        with(sharedPref.edit()) {
            putFloat(WEIGHT + position, data[position].weight.toFloat())
            putString(WEIGHT_UNIT + position, data[position].weightUnit.toString())
            putFloat(HEIGHT + position, data[position].height.toFloat())
            putString(HEIGHT_UNIT + position, data[position].heightUnit.toString())
            putFloat(BMI + position, data[position].bmi.toFloat())
            putString(DATE + position, data[position].date)
            apply()
        }
    }

    fun load(): FixedStack<BmiRecordData> {
        val stack = FixedStack<BmiRecordData>(HistoryActivity.MAX_HISTORY_SIZE)
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)
        val size = sharedPref.getInt(DATA_SIZE, 0)
        repeat(size) {
            loadRecord(sharedPref, it, stack)
        }
        return stack
    }

    private fun loadRecord(
        sharedPref: SharedPreferences,
        position: Int,
        stack: FixedStack<BmiRecordData>
    ) {
        val weight = sharedPref.getFloat(WEIGHT + position, 0F).toDouble()
        val weightUnitName = sharedPref.getString(WEIGHT_UNIT + position, "unknown unit")
        val height = sharedPref.getFloat(HEIGHT + position, 0F).toDouble()
        val heightUnitName = sharedPref.getString(HEIGHT_UNIT + position, "unknown unit")
        val bmi = sharedPref.getFloat(BMI + position, 0F).toDouble()
        val date = sharedPref.getString(DATE + position, "unknown date")
        if (weight != 0.0 && weight != 0.0 && bmi != 0.0 && weightUnitName != null && heightUnitName != null && date != null) {
            Log.d("loading", "works for $position")
            val weightUnit = WeightUnit.getUnit(weightUnitName)
            val heightUnit = HeightUnit.getUnit(heightUnitName)
            stack.push(BmiRecordData(weight, weightUnit, height, heightUnit, bmi, date))
        }
    }
}