package com.morozov.diary.di

import com.morozov.diary.fragments.cards.CardsPresenter
import com.morozov.diary.fragments.editor.EditorPresenter
import com.morozov.diary.fragments.viewing.ThinkViewingPresenter
import dagger.Component

@Component(modules = arrayOf(DiaryModule::class))
interface DiaryComponent {

    fun inject(presenter: ThinkViewingPresenter)

    fun inject(presenter: CardsPresenter)

    fun inject(presenter: EditorPresenter)
}