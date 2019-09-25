package com.morozov.experiments.adapters.results

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.experiments.R
import com.morozov.experiments.adapters.ListAdapter
import com.morozov.experiments.fragments.cards.mvp.model.ExFixingResultModel

class ResultsAdapter: ListAdapter<ExFixingResultModel, ResultsViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): ResultsViewHolder =
        ResultsViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_example_fixing_result,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: ResultsViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}