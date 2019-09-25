package com.morozov.experiments.fragments.exercises.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsView: MvpView {

    fun showTitle(title: String)

    fun showQuestion(description: String)

    fun showVariants(variants: List<String>)

    fun setFinishEnabled(boolean: Boolean)
}