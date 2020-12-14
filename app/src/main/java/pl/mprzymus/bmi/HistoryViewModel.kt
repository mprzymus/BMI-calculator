package pl.mprzymus.bmi

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.mprzymus.bmi.history.FixedStack
import pl.mprzymus.bmi.history.database.HistoryDao
import pl.mprzymus.bmi.history.database.model.BmiRecordData

class HistoryViewModel (
    val database: HistoryDao,
    application: Application) : AndroidViewModel(application) {

    val history = FixedStack<BmiRecordData>(20)

    fun save(data: FixedStack<BmiRecordData>) {
        Log.wtf("db","robie cos")
        viewModelScope.launch {
            database.clear()
            data.forEach { record -> database.insert(record) }
        }
    }

    fun save() {
        save(history)
    }

    fun load()  {
        viewModelScope.launch {
            val list = database.getAll()
            history.clear()
            list.forEach{record -> history.push(record)}
        }
    }
}