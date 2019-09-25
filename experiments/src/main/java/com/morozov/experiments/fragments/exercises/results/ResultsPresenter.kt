package com.morozov.experiments.fragments.exercises.results

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.ExperimentsLoader
import javax.inject.Inject

@InjectViewState
class ResultsPresenter: MvpPresenter<ResultsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadResult(position: Int) {
        val experiments = experimentsLoader.getExperiments()

        viewState.showTitle(experiments[position].title)
        viewState.showResult(experiments[position].conclusion)

        if (position + 1 >= experiments.size)
            viewState.hideButtonNext()
        else
            viewState.showButtonNext(experiments[position + 1].title, position + 1)
    }
}