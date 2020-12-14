package pl.mprzymus.bmi.history.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "bmi_records")
class BmiRecordData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "weight_unit")
    val weightUnit: WeightUnit,
    @ColumnInfo(name = "height")
    val height: Double,
    @ColumnInfo(name = "height_unit")
    val heightUnit: HeightUnit,
    @ColumnInfo(name = "bmi")
    val bmi: Double,
    @ColumnInfo(name = "date")
    val date: String
) : Serializable {
    constructor(
        weight: Double,
        weightUnit: WeightUnit,
        height: Double,
        heightUnit: HeightUnit,
        bmi: Double,
        date: String
    ) :
            this(0L, weight, weightUnit, height, heightUnit, bmi, date)

    fun getData(): String {
        return String.format(
            "%.1f%s, %.1f%s, BMI: %.1f",
            weight,
            weightUnit,
            height,
            heightUnit,
            bmi
        )
    }


}