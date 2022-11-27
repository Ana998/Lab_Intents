package mk.ukim.finki.lab_intents.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mk.ukim.finki.lab_intents.R

class ActivityViewAdapter(private val data: MutableList<String>):
    RecyclerView.Adapter<ActivityViewAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private lateinit var textView: TextView
        private var currentString: String? = null

        init {
            textView = view.findViewById(R.id.txtOutput)
        }

        fun bind(currentString: String){
            this.currentString = currentString
            this.textView.text = currentString
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_implicit, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}