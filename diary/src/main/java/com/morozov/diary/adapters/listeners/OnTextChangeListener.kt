package com.morozov.diary.adapters.listeners

interface OnTextChangeListener {

    fun onTextChanged(position: Int, count: Int, symbolSet: String)
}