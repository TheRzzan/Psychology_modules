package com.morozov.experiments.fragments.fix.results

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.FixingLoader
import javax.inject.Inject

@InjectViewState
class ResultsPresenter: MvpPresenter<ResultsView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadResult(position: Int) {
        val fixings = fixingLoader.getFixings()

        viewState.showTitle(fixings[position].title)
        viewState.showResult(fixings[position].description)

        if (position + 1 >= fixings.size)
            viewState.hideButtonNext()
        else
            viewState.showButtonNext(fixings[position + 1].title, position + 1)
    }
}