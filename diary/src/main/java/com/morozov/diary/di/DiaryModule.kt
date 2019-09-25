package com.morozov.diary.di

import com.morozov.diary.domain.data.ThinkLoader
import com.morozov.diary.domain.data.ThinkLoaderImpl
import com.morozov.diary.domain.data.ThinkSaver
import dagger.Module
import dagger.Provides

@Module
class DiaryModule {

    @Provides
    fun thinkLoader(): ThinkLoader = ThinkLoaderImpl()

    @Provides
    fun thinkSaver(): ThinkSaver = ThinkLoaderImpl()
}