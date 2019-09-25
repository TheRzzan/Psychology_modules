package com.morozov.experiments.adapters.listeners

interface OnTextChangeListener {

    fun onTextChanged(position: Int, count: Int, symbolSet: String)
}