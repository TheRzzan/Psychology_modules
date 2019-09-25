package com.morozov.diary.fragments.viewing

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.diary.DefaultApplication
import com.morozov.diary.domain.data.ThinkLoader
import java.util.*
import javax.inject.Inject

@InjectViewState
class ThinkViewingPresenter: MvpPresenter<ThinkViewingView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun showThink(date: Date) {
        val thinkByDate = thinkLoader.getThinkByDate(date) ?: return
        viewState.showThink(thinkByDate)
    }
}