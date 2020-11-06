package pl.mprzymus.bmi.history

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import pl.mprzymus.bmi.history.model.HeightUnit

class HeightUnitTest : FunSpec({

    test("CENTIMETER to string should be cm") {
        HeightUnit.CENTIMETER.toString() shouldBe "cm"
    }

    test("INCH to string should be in") {
        HeightUnit.INCH.toString() shouldBe "in"
    }
})
