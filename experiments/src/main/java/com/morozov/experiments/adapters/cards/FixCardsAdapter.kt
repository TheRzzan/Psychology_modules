package com.morozov.experiments.adapters.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.experiments.R
import com.morozov.experiments.adapters.ListAdapter
import com.morozov.experiments.adapters.listeners.OnItemClickListener

class FixCardsAdapter(private val listener: OnItemClickListener) : ListAdapter<String, FixCardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): FixCardsViewHolder =
        FixCardsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_experiment_fixing_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: FixCardsViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}