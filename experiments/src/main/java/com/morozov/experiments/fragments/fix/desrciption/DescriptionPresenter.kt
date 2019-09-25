package com.morozov.experiments.fragments.fix.desrciption

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.FixingLoader
import javax.inject.Inject

@InjectViewState
class DescriptionPresenter: MvpPresenter<DescriptionView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun showFixing(exercisePos: Int) {
        val fixing = fixingLoader.getFixings()[exercisePos]

        viewState.showData(fixing.title, fixing.description)
    }
}