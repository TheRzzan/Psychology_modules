package com.morozov.experiments.domain.data

import com.morozov.experiments.fragments.cards.mvp.model.ExperimentModel

interface ExperimentsLoader {

    fun getExperiments(): List<ExperimentModel>
}