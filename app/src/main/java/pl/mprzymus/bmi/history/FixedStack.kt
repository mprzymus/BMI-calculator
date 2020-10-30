package pl.mprzymus.bmi.history

import java.util.*

class FixedStack<T>(private val maxSize: Int) : Stack<T>() {
    override fun push(item: T): T {
        while (size >= maxSize) {
            removeAt(0)
        }
        return super.push(item)
    }
}