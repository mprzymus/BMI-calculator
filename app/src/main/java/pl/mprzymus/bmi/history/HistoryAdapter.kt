package pl.mprzymus.bmi.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.mprzymus.bmi.R

class HistoryAdapter<T>(private val dataSet: FixedStack<T>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val number: TextView = itemView.findViewById(R.id.history_number)
        val textView: TextView = itemView.findViewById(R.id.itemDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            HistoryViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_row_layout, parent, false)

        return HistoryViewHolder(textView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.number.text = String.format("%d.", position + 1)
        holder.textView.text = dataSet.getFifo(position).toString()
    }

    override fun getItemCount() = dataSet.size
}