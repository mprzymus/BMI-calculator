package pl.mprzymus.bmi

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import pl.mprzymus.bmi.bmi_categories_handlers.CategoryHandler
import pl.mprzymus.bmi.bmi_count.BmiUnitsDirector
import pl.mprzymus.bmi.databinding.ActivityMainBinding
import pl.mprzymus.bmi.history.FixedStack
import pl.mprzymus.bmi.history.HistoryActivity
import pl.mprzymus.bmi.history.HistoryStackSaver
import pl.mprzymus.bmi.history.database.HistoryDatabase
import pl.mprzymus.bmi.history.database.model.BmiRecordData
import java.text.DecimalFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var unitsDirector: BmiUnitsDirector
    private var handler: CategoryHandler = CategoryHandler()
    private var defaultColor: Int = 0
    private lateinit var viewModel: HistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        defaultColor = binding.bmiTV.currentTextColor
        unitsDirector = BmiUnitsDirector(
            listOf(getString(R.string.height_cm), getString(R.string.height_uk)),
            listOf(getString(R.string.mass_kg), getString(R.string.mass_uk))
        )
        val dataSource = HistoryDatabase.getInstance(application).historyDao
        val viewModelFactory = HistoryViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(HistoryViewModel::class.java)
        viewModel.load()
    }

    override fun onStop() {
        super.onStop()
        viewModel.save()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("result", binding.bmiTV.text.toString())
        outState.putSerializable("units", unitsDirector)
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
        unitsDirector = savedInstanceState.getSerializable("units") as BmiUnitsDirector
        binding.heightTV.text = unitsDirector.heightUnitsStrings[unitsDirector.index]
        binding.massTV.text = unitsDirector.weightUnitsStrings[unitsDirector.index]
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
                    val weightTooLow = unitsDirector.getCurrentValidator().isWeightTooLow(weight)
                    val weightTooHigh = unitsDirector.getCurrentValidator().isWeightTooHigh(weight)
                    val heightTooLow = unitsDirector.getCurrentValidator().isHeightTooLow(height)
                    val heightTooHigh = unitsDirector.getCurrentValidator().isHeightTooHigh(height)
                    if (!weightTooHigh && !weightTooLow && !heightTooHigh && !heightTooLow) {
                        handleValidInput(height, weight)
                    } else {
                        handleIncorrectValues(
                            weightTooHigh, weightTooLow, heightTooHigh, heightTooLow
                        )
                    }
                }
            }
        }
    }

    private fun ActivityMainBinding.handleIncorrectValues(
        weightTooHigh: Boolean, weightTooLow: Boolean, heightTooHigh: Boolean, heightTooLow: Boolean
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
        val bmi = unitsDirector.countBmi(height, weight)
        handler.handleBmiColor(bmi, bmiTV, defaultColor)
        val df = DecimalFormat("#.##")
        val date = LocalDateTime.now()
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm")
        val dateString = dateFormatter.format(date)
        bmiTV.text = df.format(bmi)
        viewModel.history.push(
            BmiRecordData(
                weight,
                unitsDirector.getCurrentWeightUnit(),
                height,
                unitsDirector.getCurrentHeightUnit(),
                bmi,
                dateString
            )
        )
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
                    unitsDirector.switchUnits(heightTV, massTV)
                }
                true
            }
            R.id.show_history -> {
                Log.d("history", "not implemented yet")
                val intent = Intent(this, HistoryActivity::class.java).apply {
                    putExtra(HistoryActivity.HISTORY_KEY, viewModel.history)
                }
                startActivity(intent)
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