package com.morozov.diary.domain.data

import com.morozov.diary.domain.models.ThinkModel

interface ThinkSaver {

    fun saveNew(think: ThinkModel)

    fun overwriteThink(think: ThinkModel)
}