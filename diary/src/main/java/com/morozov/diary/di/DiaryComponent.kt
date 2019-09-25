package com.morozov.diary.di

import dagger.Component

@Component(modules = arrayOf(DiaryModule::class))
interface DiaryComponent {
}