package com.morozov.experiments.di

import com.morozov.experiments.fragments.cards.mvp.presenter.CardsPresenter
import dagger.Component

@Component(modules = arrayOf(ExperimentsModule::class))
interface ExperimentsComponent {

    fun inject(presenter: CardsPresenter)
}