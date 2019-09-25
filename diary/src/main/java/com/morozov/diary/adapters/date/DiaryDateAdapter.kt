package com.morozov.diary.adapters.date

import android.view.LayoutInflater
import android.view.ViewGroup
import com.morozov.diary.R
import com.morozov.diary.adapters.ListAdapter

class DiaryDateAdapter: ListAdapter<Pair<Int, String>, DiaryDateViewHolder>() {

    override fun onCreateViewHolder(container: ViewGroup, p1: Int): DiaryDateViewHolder =
        DiaryDateViewHolder(
            LayoutInflater.from(container.context).inflate(
                R.layout.item_diary_date_card,
                container,
                false
            )
        )

    override fun onBindViewHolder(holder: DiaryDateViewHolder, position: Int) {
        holder.populate(data()[position])
    }
}