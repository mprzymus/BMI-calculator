package pl.mprzymus.bmi.history

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import pl.mprzymus.bmi.history.model.BmiRecordData
import pl.mprzymus.bmi.history.model.HeightUnit
import pl.mprzymus.bmi.history.model.WeightUnit

class BmiRecordDataTest : FunSpec({

    val data = BmiRecordData(70.0, WeightUnit.KILOGRAM, 180.0, HeightUnit.CENTIMETER, 23.5)

    test("bmi info to string test") {
        data.getData() shouldBe "weight: 70.0kg, height: 180.0cm, bmi: 23.5"
    }
})
