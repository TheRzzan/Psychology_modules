package com.morozov.experiments.adapters.test

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.experiments.R
import com.morozov.experiments.adapters.ListAdapter
import com.morozov.experiments.adapters.listeners.OnTextChangeListener

class TestAdapter(private val listener: OnTextChangeListener): ListAdapter<String, TestViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): TestViewHolder =
        TestViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_experiment_test,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.populate(data().get(position), position, listener)
    }
}