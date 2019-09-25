package com.morozov.experiments.fragments.exercises.desrciption

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface DescriptionView: MvpView {

    fun showData(name: String, description: String)
}