package pl.mprzymus.bmi

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
        //TODO oprogramowac zapamietywanie stanu ui (tam gdzie potrzeba)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        defaultColor = savedInstanceState.getInt("color")
        handler = CategoryHandler()
        var bmi = savedInstanceState.getString("result")
        binding.bmiTV.text = bmi
        if (bmi != null && bmi != getString(R.string.empty_value)) {
            bmi = bmi.replace(",", ".")
            handler.handleBmi(bmi.toDouble(), binding.bmiTV, defaultColor)
        }
        calculator = savedInstanceState.getSerializable("units") as BmiUnitsDirector
        binding.heightTV.text = calculator.heightUnits[calculator.index]
        binding.massTV.text = calculator.weightUnits[calculator.index]
        //TODO odt. stanu
    }

    //TODO kliknięcie wyniku wyświetla aktywność z opisem wyniku, 3 elementy

    fun count(view: View) {
        binding.apply {
            //TODO sprawdzanie danych wejsciowych
            when {
                heightET.text.isBlank() || massET.text.isBlank() -> {
                    if (heightET.text.isBlank()) {
                        handleEmptyInput(heightET, R.string.height_is_empty)
                    }
                    if (massET.text.isBlank()) {
                        handleEmptyInput(massET, R.string.mass_is_empty)
                    }
                }
                else -> {
                    val bmi = calculator.countBmi(
                        heightET.text.toString().toDouble(),
                        massET.text.toString().toDouble()
                    )
                    handler.handleBmi(bmi, bmiTV, defaultColor)
                    val df = DecimalFormat("#.##")
                    bmiTV.text = df.format(bmi)
                }
            }
        }
    }

    private fun ActivityMainBinding.handleEmptyInput(editText: EditText, emptyInfo: Int) {
        editText.error = getString(emptyInfo)
        bmiTV.text = editText.text.toString()
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
}