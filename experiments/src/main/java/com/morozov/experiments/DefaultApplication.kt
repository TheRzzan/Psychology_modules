package com.morozov.experiments

import android.app.Application
import com.morozov.experiments.di.DaggerExperimentsComponent
import com.morozov.experiments.di.ExperimentsComponent
import com.morozov.experiments.di.ExperimentsModule

class DefaultApplication: Application() {
    companion object {
        lateinit var experimentsComponent: ExperimentsComponent
    }

    override fun onCreate() {
        super.onCreate()

        experimentsComponent = DaggerExperimentsComponent
            .builder()
            .experimentsModule(ExperimentsModule())
            .build()
    }
}