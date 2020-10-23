package pl.mprzymus.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import pl.mprzymus.bmi.bmi_count.Bmi
import pl.mprzymus.bmi.bmi_count.BmiMetric
import pl.mprzymus.bmi.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var calculator: Bmi = BmiMetric()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //TODO oprogramowac zapamietywanie stanu ui (tam gdzie potrzeba)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //savedInstanceState.putBinder("result", binding.bmiTV.text)
        //TODO odt. stanu
    }

    //TODO kliknięcie wyniku wyświetla aktywność z opisem wyniku, 3 elementy

    fun count(view: View) {
        binding.apply {
            //TODO oprogramowac liczenie bmi i sprawdzanie danych wejsciowych
            //TODO kolor zależny od wyniku
            //TODO Możlowość zmiany jednostek
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
}