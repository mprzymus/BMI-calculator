package pl.mprzymus.bmi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.mprzymus.bmi.bmi_categories_handlers.CategoryHandler
import pl.mprzymus.bmi.databinding.ActivityBmiInfoBinding

class BmiInfo : AppCompatActivity() {

    private val handler = CategoryHandler()
    lateinit var binding: ActivityBmiInfoBinding

    companion object {
        const val BMI = "BMI"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bmi = intent.getStringExtra(BMI)
        chooseBmiText(bmi)
        binding.bmiTV.text = bmi
    }

    private fun chooseBmiText(bmi: String?) {
        val emptyValue = getString(R.string.empty_value)
        if (bmi.equals(emptyValue)) {
            binding.bmiInfoTV.text = getString(R.string.empty_value_info)
        } else {
            val bmiAsDouble: Double = bmi?.replace(",", ".")?.toDouble() ?: 0.0
            chooseInfo(bmiAsDouble)
        }
    }

    private fun chooseInfo(bmiAsDouble: Double) {
        when {
            handler.isTooLow(bmiAsDouble) -> {
                binding.bmiInfoTV.text = getString(R.string.bmi_to_low)
            }
            handler.isTooHigh(bmiAsDouble) -> {
                binding.bmiInfoTV.text = getString(R.string.bmi_to_high)
            }
            else -> {
                binding.bmiInfoTV.text = getString(R.string.bmi_ok)
            }
        }
    }
}