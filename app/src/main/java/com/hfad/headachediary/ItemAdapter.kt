package com.hfad.headachediary

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.headachediary.DB.HeadacheDao
import com.hfad.headachediary.Entity.HeadacheEntity
import com.hfad.headachediary.Entity.HeadacheTuple
import com.hfad.headachediary.Entity.MedicinesEntity
import java.text.SimpleDateFormat
import java.util.Locale

class ItemAdapter(
    private val context: Context,
    private val listener: Listener,
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface Listener {
        fun onClick(data: HeadacheTuple, position: Int)
    }

    private var items = emptyList<HeadacheTuple>()
    inner class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var dateItem: TextView
        var localizationItem: TextView
        var durationItem: TextView
        var characterItem: TextView
        var medicinesItem: TextView
        var cardView: CardView

        init {
            dateItem = itemsView.findViewById(R.id.date_item)
            localizationItem = itemsView.findViewById(R.id.localization_item)
            durationItem = itemsView.findViewById(R.id.duration_item)
            characterItem = itemsView.findViewById(R.id.character_item)
            medicinesItem = itemsView.findViewById(R.id.medicines_item)
            cardView = itemsView.findViewById(R.id.card_item)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ViewHolder {
        val view: CardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_item, parent, false)
        as CardView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemAdapter.ViewHolder, position: Int) {
        val current = items[position]
        val cardView = holder.cardView
        val localizationList = mutableListOf<String>()
        val characterList = mutableListOf<String>()
        val medicinesList = mutableListOf<String>()
        for (i in 0 until current.localizationList.size) {
            localizationList.add(current.localizationList[i].localizationItem)
        }
        for (i in 0 until current.characterList.size) {
            characterList.add(current.characterList[i].characterItem)
        }
        for (i in 0 until current.medicinesList.size) {
            medicinesList.add("${current.medicinesList[i].medicinesName ?: "-"}, " +
                    "${current.medicinesDoseList[i].medicinesDose ?: 0} ${holder.itemView.context.getString(R.string.dose_value)}, " +
                    "${current.medicinesDoseList[i].medicinesCount ?: 0} ${holder.itemView.context.getString(R.string.count_pieces)}")
        }
        val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val formatDate = date.format(current.item.dateItem)
        holder.dateItem.text = holder.itemView.context.getString(R.string.text_date,
            formatDate.toString())
        holder.localizationItem.text = holder.itemView.context.getString(R.string.text_localization,
            localizationList.joinToString(separator = ", "))
        holder.characterItem.text = holder.itemView.context.getString(R.string.text_character,
            characterList.joinToString(separator = ", "))
        holder.durationItem.text = holder.itemView.context.getString(R.string.text_duration,
            current.item.duration.toString())
        holder.medicinesItem.text = holder.itemView.context.getString(R.string.text_medicines,
            medicinesList.joinToString(separator = ", "))

        cardView.setOnClickListener { listener.onClick(current, position) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun setItems(items: List<HeadacheTuple>) {
        this.items = items
        notifyDataSetChanged()
    }
}