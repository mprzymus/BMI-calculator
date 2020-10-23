package pl.mprzymus.bmi.bmi_categories_handlers

import android.graphics.Color
import android.widget.TextView

class CategoryHandler {
    companion object {
        const val BOTTOM_BORDER = 18.5
        const val UPPER_BORDER = 25.0
    }
    fun handleBmi(bmi: Double, view: TextView) {
        if (isTooLow(bmi)) {
            view.setTextColor(Color.RED)
        }
        else if (isTooHigh(bmi)) {
            view.setTextColor(Color.RED)
        }
    }

    private fun isTooLow(bmi: Double): Boolean {
        return bmi < BOTTOM_BORDER
    }
    private fun isTooHigh(bmi: Double): Boolean {
        return bmi > UPPER_BORDER
    }
}