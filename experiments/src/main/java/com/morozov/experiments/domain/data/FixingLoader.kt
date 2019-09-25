package com.morozov.experiments.domain.data

import com.morozov.experiments.fragments.cards.mvp.model.FixingExerciseModel

interface FixingLoader {

    fun getFixings(): List<FixingExerciseModel>
}