package com.morozov.experiments.fragments.exercises.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.ExperimentsLoader
import javax.inject.Inject

@InjectViewState
class TestsPresenter: MvpPresenter<TestsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadData(position: Int) {
        val experiments = experimentsLoader.getExperiments()

        viewState.showTitle(experiments[position].title)
        viewState.showQuestion(experiments[position].question)
        viewState.showVariants(experiments[position].variants)
    }
}