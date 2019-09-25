package com.morozov.diary.fragments.editor

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.diary.DefaultApplication
import com.morozov.diary.domain.data.ThinkLoader
import com.morozov.diary.domain.data.ThinkSaver
import com.morozov.diary.domain.models.ThinkModel
import java.util.*
import javax.inject.Inject

@InjectViewState
class EditorPresenter: MvpPresenter<EditorView>() {

    @Inject
    lateinit var thinkSaver: ThinkSaver

    @Inject
    lateinit var thinkLoader: ThinkLoader

    lateinit var dateNew: Date
    lateinit var dateOld: Date

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    fun initNewThink(date: Date) {
        dateNew = Date()
        viewState.setDate(date)
    }

    fun loadOldThink(date: Date) {
        dateOld = date
        viewState.setDate(date)

        val thinkByDate = thinkLoader.getThinkByDate(date) ?: return
        viewState.showThink(thinkByDate)
        viewState.hideSeekBar()
    }

    fun saveNewThink(think: ThinkModel) {
        thinkSaver.saveNew(think)
    }

    fun saveOldThink(think: ThinkModel) {
        thinkSaver.overwriteThink(think)
    }
}