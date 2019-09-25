package com.morozov.experiments.di

import com.morozov.experiments.fragments.cards.mvp.presenter.CardsPresenter
import com.morozov.experiments.fragments.exercises.desrciption.DescriptionPresenter
import com.morozov.experiments.fragments.exercises.results.ResultsPresenter
import com.morozov.experiments.fragments.exercises.tests.TestsPresenter
import dagger.Component

@Component(modules = arrayOf(ExperimentsModule::class))
interface ExperimentsComponent {

    fun inject(presenter: CardsPresenter)

    fun inject(presenter: DescriptionPresenter)

    fun inject(presenter: ResultsPresenter)

    fun inject(presenter: TestsPresenter)

    fun inject(presenter: com.morozov.experiments.fragments.fix.desrciption.DescriptionPresenter)

    fun inject(presenter: com.morozov.experiments.fragments.fix.results.ResultsPresenter)

    fun inject(presenter: com.morozov.experiments.fragments.fix.tests.TestsPresenter)
}