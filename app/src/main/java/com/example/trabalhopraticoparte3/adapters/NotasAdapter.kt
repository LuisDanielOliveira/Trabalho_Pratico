package com.example.trabalhopraticoparte3.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalhopraticoparte3.R
import com.example.trabalhopraticoparte3.entities.NotasEntity

class NotasAdapter internal constructor(
        context: Context
) : RecyclerView.Adapter<NotasAdapter.NotasViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var no_tas = emptyList<NotasEntity>()
    class NotasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val notasItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotasViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return NotasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NotasViewHolder, position: Int) {
        val current = no_tas[position]
        holder.notasItemView.text = current.id.toString() + " - " + current.titulo + "-" + current.notas
    }

    internal fun setNotas(notasEntities: List<NotasEntity>) {
        this.no_tas = notasEntities
        notifyDataSetChanged()
    }

    override fun getItemCount() = no_tas.size
}

