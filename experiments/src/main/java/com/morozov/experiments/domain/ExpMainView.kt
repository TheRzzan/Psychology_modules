package com.morozov.experiments.domain

interface ExpMainView {

    fun showExpCards()

    fun showExDescr(position: Int)

    fun showExFixDescr(position: Int)

    fun showExTest(position: Int)

    fun showExFixTest(position: Int)

    fun showExResults(position: Int)

    fun showExFixResults(position: Int)

    fun showExpDiaryCards()
}