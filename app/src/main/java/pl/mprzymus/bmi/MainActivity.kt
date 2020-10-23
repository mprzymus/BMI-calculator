package pl.mprzymus.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import pl.mprzymus.bmi.bmi_count.Bmi
import pl.mprzymus.bmi.bmi_count.BmiMetric
import pl.mprzymus.bmi.databinding.ActivityMainBinding


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
        Log.wtf("test", "wtf")
        binding.apply {
            //TODO oprogramowac liczenie bmi i sprawdzanie danych wejsciowych
            //TODO kolor zależny od wyniku
            //TODO Możlowość zmiany jednostek
            if (heightET.text.isBlank()) {
                heightET.error = getString(R.string.height_is_empty)
                bmiTV.text = heightET.text.toString()
            }
        }
    }
}