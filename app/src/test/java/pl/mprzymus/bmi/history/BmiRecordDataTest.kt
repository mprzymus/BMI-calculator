package pl.mprzymus.bmi.history

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import pl.mprzymus.bmi.history.database.model.BmiRecordData
import pl.mprzymus.bmi.history.database.model.HeightUnit
import pl.mprzymus.bmi.history.database.model.WeightUnit

class BmiRecordDataTest : FunSpec({

    val data = BmiRecordData(70.0, WeightUnit.KILOGRAM, 180.0, HeightUnit.CENTIMETER, 23.5, "date")

    test("bmi info to string test") {
        data.getData() shouldBe "70.0kg, 180.0cm, BMI: 23.5"
    }
})
