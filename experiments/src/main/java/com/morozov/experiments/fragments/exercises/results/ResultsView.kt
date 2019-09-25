package com.morozov.experiments.fragments.exercises.results

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ResultsView: MvpView {

    fun showTitle(title: String)

    fun showResult(result: String)

    fun showButtonNext(text: String, position: Int)

    fun hideButtonNext()
}