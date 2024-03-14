package com.example.kitharam.Fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kitharam.R

class Adapter(private val dataSet: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.Level)

        init {
            itemView.setOnClickListener {
                onItemClick(dataSet[adapterPosition].toString())
            }
//            button.setOnClickListener {
//                onItemClick(dataSet[adapterPosition].toString())
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataSet[position].toString()
    }

    override fun getItemCount() = dataSet.size
}