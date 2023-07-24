package com.example.testovoe18

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class HistoryAdapter(private val context: Context, var cards: List<Card>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.textViewCategory.text = card.category
        if (card.income) {
            holder.textViewRes.text = card.value.toString()
            holder.textViewRes.setTextColor(ContextCompat.getColor(context, R.color.green))
        } else {
            holder.textViewRes.text = "-"+card.value.toString()
            holder.textViewRes.setTextColor(ContextCompat.getColor(context, R.color.red))
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewCategory: TextView = itemView.findViewById(R.id.textViewCategory)
        val textViewRes: TextView = itemView.findViewById(R.id.textViewRes)
    }
}