package pl.mprzymus.bmi.history

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.mprzymus.bmi.R
import pl.mprzymus.bmi.databinding.HistoryLayoutBinding
import pl.mprzymus.bmi.history.database.model.BmiRecordData

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: HistoryLayoutBinding
    private lateinit var viewAdapter: HistoryAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        const val HISTORY_KEY = "history"
        const val MAX_HISTORY_SIZE = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HistoryLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewManager = LinearLayoutManager(this)
        val data = intent.getSerializableExtra(HISTORY_KEY) as ArrayList<BmiRecordData>
        viewAdapter = HistoryAdapter(FixedStack(data, MAX_HISTORY_SIZE))
        recyclerView = findViewById<RecyclerView>(R.id.history_recycle).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}