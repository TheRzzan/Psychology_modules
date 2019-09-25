package com.morozov.diary.adapters.think.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.diary.R
import com.morozov.diary.adapters.ListAdapter
import com.morozov.diary.adapters.listeners.OnItemClickListener

class DiaryThinkAdapter(private val listener: OnItemClickListener): ListAdapter<Pair<String, String>, DiaryThinkViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryThinkViewHolder =
        DiaryThinkViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_think_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryThinkViewHolder, position: Int) {
        holder.populate(data()[position], position, listener)
    }
}