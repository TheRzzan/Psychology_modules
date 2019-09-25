package com.morozov.diary.domain.data

import com.morozov.diary.domain.models.ThinkModel
import java.util.*

interface ThinkLoader {

    fun getThinks(): List<ThinkModel>

    fun getThinkByDate(date: Date): ThinkModel?
}