package com.morozov.experiments.fragments.cards.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.ExperimentsLoader
import com.morozov.experiments.domain.data.FixingLoader
import com.morozov.experiments.fragments.cards.mvp.view.CardsView
import javax.inject.Inject

@InjectViewState
class CardsPresenter: MvpPresenter<CardsView>() {

    @Inject
    lateinit var experimentsLoader: ExperimentsLoader

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadDataExperiments() {
        val titles: MutableList<String> = mutableListOf()

        for (item in experimentsLoader.getExperiments()) {
            titles.add(item.title)
        }

        viewState.showDataExperiments(titles)
    }

    fun loadDataFixing() {
        val titles: MutableList<String> = mutableListOf()

        for (item in fixingLoader.getFixings()) {
            titles.add(item.title)
        }

        viewState.showDataFixing(titles)
    }
}