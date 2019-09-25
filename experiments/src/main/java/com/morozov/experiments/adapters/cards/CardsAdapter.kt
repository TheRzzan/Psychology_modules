package com.morozov.experiments.adapters.cards

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.experiments.R
import com.morozov.experiments.adapters.ListAdapter
import com.morozov.experiments.adapters.listeners.OnItemClickListener

class CardsAdapter(private val listener: OnItemClickListener) : ListAdapter<String, CardsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): CardsViewHolder =
        CardsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_experiment_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}