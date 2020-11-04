package pl.mprzymus.bmi.history

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class FixedStackTest : FunSpec({

    val maxSize = 2
    var stack = FixedStack<Int>(maxSize)


    beforeEach {
        stack = FixedStack(maxSize)
    }

    test("simple push") {
        val element = 1
        stack.push(element)
        stack.pop() shouldBe element
    }
    test("push over size test") {
        listOf(1, 2, 3, 4).forEach(stack::push)
        stack.size shouldBe maxSize
        stack.pop() shouldBe 4
        stack.pop() shouldBe 3
    }
    test("fifo like index get test") {
        stack = FixedStack(4)
        listOf(1, 2, 3, 4).forEach(stack::push)
        stack.getFifo(0) shouldBe 4
        stack.getFifo(1) shouldBe 3
        stack.getFifo(2) shouldBe 2
        stack.getFifo(3) shouldBe 1
    }
    test("fifo like index get should throw with invalid input") {
        listOf(1, 2).forEach(stack::push)
        shouldThrow<IllegalArgumentException> { stack.getFifo(-1) }
        shouldThrow<IllegalArgumentException> { stack.getFifo(maxSize+1) }
    }
})
