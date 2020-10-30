package pl.mprzymus.bmi.bmi_count

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

class BmiEnglishUnitsTest : FunSpec({

    val tested = BmiEnglishUnits()
    test("should count BMI properly with good input") {
        tested.countBmi(85.0, 200.0) shouldBe (19.55 plusOrMinus 0.1)
    }

    test("BMI should be 0 when weight is 0") {
        tested.countBmi(180.0, 0.0) shouldBe 0.0
    }

    test("should return infinity when height is 0") {
        tested.countBmi(0.0, 70.0) shouldBe Double.POSITIVE_INFINITY
    }

})
