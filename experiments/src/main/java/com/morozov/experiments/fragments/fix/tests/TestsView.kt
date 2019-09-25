package com.morozov.experiments.fragments.fix.tests

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.experiments.fragments.cards.mvp.model.ExFixingResultModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface TestsView: MvpView {

    fun showData(description: String, data: List<String>)

    fun showResults(data: List<ExFixingResultModel>)

    fun setFinishEnabled(boolean: Boolean)

    fun outOfTest()

    fun setButtonText(text: String)
}