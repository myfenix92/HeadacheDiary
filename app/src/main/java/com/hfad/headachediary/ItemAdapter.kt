package com.hfad.headachediary

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val context: Context,
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var items = emptyList<HeadacheTuple>()
    inner class ViewHolder(itemsView: View) : RecyclerView.ViewHolder(itemsView) {
        var dateItem: TextView
        var localizationItem: TextView
        var durationItem: TextView
        var characterItem: TextView
        var medicinesItem: TextView

        init {
            dateItem = itemsView.findViewById(R.id.date_item)
            localizationItem = itemsView.findViewById(R.id.localization_item)
            durationItem = itemsView.findViewById(R.id.duration_item)
            characterItem = itemsView.findViewById(R.id.character_item)
            medicinesItem = itemsView.findViewById(R.id.medicines_item)
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
        holder.dateItem.text = current.dateItem.toString()
        holder.localizationItem.text = current.localizationItem
        holder.characterItem.text = current.characterItem
        holder.medicinesItem.text = "${current.medicinesName}, ${current.medicinesDose}, ${current.medicinesCount}"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    internal fun setItems(items: List<HeadacheTuple>) {
        this.items = items
        notifyDataSetChanged()
    }
}