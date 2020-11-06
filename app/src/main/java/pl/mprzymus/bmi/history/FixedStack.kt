package pl.mprzymus.bmi.history

import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class FixedStack<T>(private val maxSize: Int) : Stack<T>(), Serializable {
    constructor(list: ArrayList<T>, maxSize: Int): this(maxSize) {
        list.forEach(this::push)
    }
    override fun push(item: T): T {
        while (size >= maxSize) {
            removeAt(0)
        }
        return super.push(item)
    }

    fun getFifo(position: Int) : T {
        if (size == 0) {
            throw IllegalArgumentException("empty collection")
        }
        if (position < 0) {
            throw IllegalArgumentException("position bigger than size")
        }
        if (position >= size) {
            throw IllegalArgumentException("position bigger than size")
        }
        val fifoPosition = size - 1 - position
        return this[fifoPosition]
    }
}