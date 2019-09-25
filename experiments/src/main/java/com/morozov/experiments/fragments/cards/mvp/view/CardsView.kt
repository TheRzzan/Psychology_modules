package com.morozov.experiments.fragments.cards.mvp.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface CardsView : MvpView {

    fun showDataExperiments(data: List<String>)

    fun showDataFixing(data: List<String>)
}