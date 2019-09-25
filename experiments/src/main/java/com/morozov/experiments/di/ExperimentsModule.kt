package com.morozov.experiments.di

import com.morozov.experiments.domain.data.ExperimentsLoader
import com.morozov.experiments.domain.data.ExperimentsLoaderImpl
import com.morozov.experiments.domain.data.FixingLoader
import com.morozov.experiments.domain.data.FixingLoaderImpl
import dagger.Module
import dagger.Provides

@Module
class ExperimentsModule {

    @Provides
    fun experimentsLoader(): ExperimentsLoader = ExperimentsLoaderImpl()

    @Provides
    fun fixingLoader(): FixingLoader = FixingLoaderImpl()
}