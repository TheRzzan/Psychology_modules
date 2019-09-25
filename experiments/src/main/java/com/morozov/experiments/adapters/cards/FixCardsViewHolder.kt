package com.morozov.experiments.adapters.cards

import android.support.v7.widget.RecyclerView
import android.view.View
import com.morozov.experiments.adapters.listeners.OnItemClickListener
import kotlinx.android.synthetic.main.item_experiment_fixing_card.view.*

class FixCardsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(name: String, pos: Int, listener: OnItemClickListener) {
        itemView.textExampleFixingName.text = name
        itemView.imageCardFixing.setOnClickListener{
            listener.onItemClick(itemView.imageCardFixing, pos)
        }
    }
}