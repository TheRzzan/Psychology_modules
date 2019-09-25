package com.morozov.diary.fragments.viewing

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.diary.domain.models.ThinkModel

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface ThinkViewingView: MvpView {

    fun showThink(think: ThinkModel)
}