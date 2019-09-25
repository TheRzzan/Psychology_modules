package com.morozov.experiments.adapters.test

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.morozov.experiments.adapters.listeners.OnTextChangeListener
import kotlinx.android.synthetic.main.item_experiment_test.view.*

class TestViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun populate(text: String, position: Int, listener: OnTextChangeListener) {
        itemView.textTestQuestion.text = text
        itemView.editTextAnswer.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null) {
                    listener.onTextChanged(position, s.length, s.toString())
                } else {
                    listener.onTextChanged(position, 0, s.toString())
                }
            }
        })
    }
}