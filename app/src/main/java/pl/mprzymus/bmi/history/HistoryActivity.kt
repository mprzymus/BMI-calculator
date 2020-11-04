package pl.mprzymus.bmi.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mprzymus.bmi.R
import pl.mprzymus.bmi.databinding.HistoryLayoutBinding

class HistoryActivity : AppCompatActivity() {
    lateinit var binding: HistoryLayoutBinding
    private val data = FixedStack<Double>(10)
    private  val viewAdapter = HistoryAdapter(data)
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewManager = LinearLayoutManager(this)
        data.clear()
        data.add(1.0)
        data.add(2.0)
        data.add(3.0)
        recyclerView = findViewById<RecyclerView>(R.id.history_recycle).apply {
            //setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}