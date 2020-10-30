package pl.mprzymus.bmi.history

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.Table5
import io.kotest.data.forAll
import io.kotest.matchers.shouldBe

class FixedStackTest : FunSpec({

    val maxSize = 2
    val stack = FixedStack<Int>(maxSize)
    test("simple push") {
        val element = 1
        stack.push(element)
        stack.pop() shouldBe element
    }
    test("push over size test") {
        listOf(1,2,3,4).forEach(stack::push)
        stack.size shouldBe maxSize
        stack.pop() shouldBe 4
        stack.pop() shouldBe 3
    }
})
