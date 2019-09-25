package com.morozov.diary.domain

import java.util.*

interface DiaMainView {

    fun showDiaryCards()

    fun showDiaryViewing(date: Date)

    fun showDiaryEditor(isNew: Boolean, date: Date)
}