package pl.mprzymus.bmi.bmi_categories_handlers

import android.graphics.Color
import android.widget.TextView

class CategoryHandler {
    companion object {
        const val BOTTOM_BORDER = 18.5
        const val UPPER_BORDER = 25.0
    }
    fun handleBmiColor(bmi: Double, view: TextView, color: Int) {
        when {
            isTooLow(bmi) -> {
                view.setTextColor(Color.RED)
            }
            isTooHigh(bmi) -> {
                view.setTextColor(Color.RED)
            }
            else -> {
                view.setTextColor(color)
            }
        }
    }

    fun isTooLow(bmi: Double): Boolean {
        return bmi < BOTTOM_BORDER
    }

    fun isTooHigh(bmi: Double): Boolean {
        return bmi > UPPER_BORDER
    }
}