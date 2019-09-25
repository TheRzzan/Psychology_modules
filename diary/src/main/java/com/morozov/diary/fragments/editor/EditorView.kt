package com.morozov.diary.fragments.editor

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.morozov.diary.domain.models.ThinkModel
import java.util.*

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface EditorView: MvpView {

    fun showButtonSave()

    fun hideButtonSave()

    fun showSeekBar()

    fun hideSeekBar()

    fun showThink(think: ThinkModel)

    fun setDate(date: Date)

    fun setIsActiveJoy(b: Boolean)
    fun setIsActiveSadness(b: Boolean)
    fun setIsActiveAnnoyance(b: Boolean)
    fun setIsActiveAnxiety(b: Boolean)
    fun setIsActiveDisgust(b: Boolean)
    fun setIsActiveInterest(b: Boolean)
    fun setIsActiveGuilt(b: Boolean)
    fun setIsActiveResentment(b: Boolean)
}