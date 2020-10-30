package pl.mprzymus.bmi

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import pl.mprzymus.bmi.bmi_categories_handlers.CategoryHandler
import pl.mprzymus.bmi.bmi_count.BmiUnitsDirector
import pl.mprzymus.bmi.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var calculator: BmiUnitsDirector
    private var handler: CategoryHandler = CategoryHandler()
    private var defaultColor: Int = 0

    companion object {
        const val WEIGHT_MIN = 30.0
        const val WEIGHT_MAX = 500.0
        const val HEIGHT_MIN = 120.0
        const val HEIGHT_MAX = 250.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        defaultColor = binding.bmiTV.currentTextColor
        calculator = BmiUnitsDirector(
            listOf(getString(R.string.height_cm), getString(R.string.height_uk)),
            listOf(getString(R.string.mass_kg), getString(R.string.mass_uk))
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", binding.bmiTV.text.toString())
        outState.putSerializable("units", calculator)
        outState.putInt("color", defaultColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        defaultColor = savedInstanceState.getInt("color")
        handler = CategoryHandler()
        var bmi = savedInstanceState.getString("result")
        binding.bmiTV.text = bmi
        if (bmi != null && bmi != getString(R.string.empty_value)) {
            bmi = bmi.replace(",", ".")
            handler.handleBmiColor(bmi.toDouble(), binding.bmiTV, defaultColor)
        }
        calculator = savedInstanceState.getSerializable("units") as BmiUnitsDirector
        binding.heightTV.text = calculator.heightUnits[calculator.index]
        binding.massTV.text = calculator.weightUnits[calculator.index]
    }

    fun count(view: View) {
        binding.apply {
            when {
                heightET.text.isBlank() || massET.text.isBlank() -> {
                    handleBlankInput()
                }
                else -> {
                    val height = heightET.text.toString().toDouble()
                    val weight = massET.text.toString().toDouble()
                    val weightTooLow = weight < WEIGHT_MIN
                    val weightTooHigh = weight > WEIGHT_MAX
                    val heightTooLow = height < HEIGHT_MIN
                    val heightTooHigh = height > HEIGHT_MAX
                    if (!weightTooHigh && ! weightTooLow && !heightTooHigh &&!heightTooLow) {
                        handleValidInput(height, weight)
                    }
                    else {
                        handleIncorrectValues(
                            weightTooHigh,
                            weightTooLow,
                            heightTooHigh,
                            heightTooLow
                        )
                    }
                }
            }
        }
    }

    private fun ActivityMainBinding.handleIncorrectValues(
        weightTooHigh: Boolean,
        weightTooLow: Boolean,
        heightTooHigh: Boolean,
        heightTooLow: Boolean
    ) {
        if (weightTooHigh) {
            handleWrongInput(massET, R.string.wrong_weight_high)
        }
        if (weightTooLow) {
            handleWrongInput(massET, R.string.wrong_weight_low)
        }
        if (heightTooHigh) {
            handleWrongInput(heightET, R.string.wrong_height_high)
        }
        if (heightTooLow) {
            handleWrongInput(heightET, R.string.wrong_height_low)
        }
    }

    private fun ActivityMainBinding.handleBlankInput() {
        if (heightET.text.isBlank()) {
            handleWrongInput(heightET, R.string.height_is_empty)
        }
        if (massET.text.isBlank()) {
            handleWrongInput(massET, R.string.mass_is_empty)
        }
    }

    private fun ActivityMainBinding.handleWrongInput(editText: EditText, emptyInfo: Int) {
        editText.error = getString(emptyInfo)
        bmiTV.text = editText.text.toString()
    }

    private fun ActivityMainBinding.handleValidInput(
        height: Double,
        weight: Double
    ) {
        val bmi = calculator.countBmi(height, weight)
        handler.handleBmiColor(bmi, bmiTV, defaultColor)
        val df = DecimalFormat("#.##")
        bmiTV.text = df.format(bmi)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.change_units -> {
                binding.apply {
                    calculator.switchUnits(heightTV, massTV)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showBmiDetails(view: View) {
        val intent = Intent(this, BmiInfo::class.java).apply {
            putExtra(BmiInfo.BMI, binding.bmiTV.text)
        }
        startActivityForResult(intent, 1)
    }
}