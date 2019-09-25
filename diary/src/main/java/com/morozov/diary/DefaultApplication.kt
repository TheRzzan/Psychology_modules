package com.morozov.diary

import android.app.Application
import com.morozov.diary.di.DaggerDiaryComponent
import com.morozov.diary.di.DiaryComponent
import com.morozov.diary.di.DiaryModule

class DefaultApplication: Application() {
    companion object {
        lateinit var diaryComponent: DiaryComponent
    }

    override fun onCreate() {
        super.onCreate()

        diaryComponent = DaggerDiaryComponent
                         .builder()
                         .diaryModule(DiaryModule())
                         .build()
    }
}