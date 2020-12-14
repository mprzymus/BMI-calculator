package pl.mprzymus.bmi.history.database

import androidx.room.TypeConverter
import pl.mprzymus.bmi.history.database.model.WeightUnit

class WeightUnitConverter {
    @TypeConverter
    fun fromUnit(value: WeightUnit): String{
        return value.name
    }

    @TypeConverter
    fun toUnit(value: String): WeightUnit = enumValueOf(value)
}