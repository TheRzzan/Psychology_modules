package com.morozov.experiments.fragments.fix.tests

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.experiments.DefaultApplication
import com.morozov.experiments.domain.data.FixingLoader
import com.morozov.experiments.fragments.cards.mvp.model.ExFixingResultModel
import javax.inject.Inject

@InjectViewState
class TestsPresenter: MvpPresenter<TestsView>() {

    @Inject
    lateinit var fixingLoader: FixingLoader

    init {
        DefaultApplication.experimentsComponent.inject(this)
    }

    fun loadData(exercisePos: Int, fixingPos: Int) {
        if (fixingPos >= fixingLoader.getFixings()[exercisePos].fixings.size) {
            viewState.outOfTest()
            return
        }

        val pairs = fixingLoader.getFixings()[exercisePos].fixings[fixingPos].pairs

        val texts: MutableList<String> = mutableListOf()

        for (pair in pairs) {
            texts.add(pair.first)
        }

        viewState.showData(fixingLoader.getFixings()[exercisePos].fixings[fixingPos].situation, texts)
        viewState.setButtonText("Проверить")
    }

    fun showResults(exercisePos: Int, fixingPos: Int, userAnswers: List<String>) {
        val pairs = fixingLoader.getFixings()[exercisePos].fixings[fixingPos].pairs

        val result: MutableList<ExFixingResultModel> = mutableListOf()
        var i = 0

        for (pair in pairs) {
            result.add(
                ExFixingResultModel(
                    pair.first,
                    userAnswers[i],
                    pair.second
                )
            )
            i++
        }

        viewState.showResults(result)

        if (fixingPos + 1 >= fixingLoader.getFixings()[exercisePos].fixings.size)
            viewState.setButtonText("Завершить")
        else
            viewState.setButtonText("Далее")
    }
}