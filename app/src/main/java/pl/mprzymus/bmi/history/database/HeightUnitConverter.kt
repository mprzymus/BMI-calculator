package pl.mprzymus.bmi.history.database

import androidx.room.TypeConverter
import pl.mprzymus.bmi.history.database.model.HeightUnit

class HeightUnitConverter {
    @TypeConverter
    fun fromUnit(value: HeightUnit): String{
        return value.name
    }

    @TypeConverter
    fun toUnit(value: String): HeightUnit = enumValueOf(value)
}