package com.morozov.experiments.fragments.exercises.desrciption

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.ExperimentsLoader
import javax.inject.Inject

@InjectViewState
class DescriptionPresenter: MvpPresenter<DescriptionView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadData(position: Int) {
        val experiments = experimentsLoader.getExperiments()

        viewState.showData(experiments[position].title,
            experiments[position].description)
    }
}