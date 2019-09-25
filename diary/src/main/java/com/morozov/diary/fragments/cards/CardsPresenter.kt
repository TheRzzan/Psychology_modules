package com.morozov.diary.fragments.cards

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.morozov.diary.DefaultApplication
import com.morozov.diary.domain.data.ThinkLoader
import com.morozov.diary.domain.models.ThinkModel
import com.morozov.diary.utility.DateConverter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@InjectViewState
class CardsPresenter: MvpPresenter<CardsView>() {

    @Inject
    lateinit var thinkLoader: ThinkLoader

    init {
        DefaultApplication.diaryComponent.inject(this)
    }

    companion object {
        var currentDate = -1
    }

    var lastMonthData: MutableList<MutableList<ThinkModel>> = mutableListOf()
    var dateList: MutableList<Date> = mutableListOf()

    fun loadData() {
        val thinksAll = thinkLoader.getThinks()
        val thinksLastMonth: MutableList<ThinkModel> = mutableListOf()
        val elements: MutableList<Pair<Int, String>> = mutableListOf()

        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")

        val todayDate = Date()

        for (item in thinksAll) {
            if (monthFormat.format(item.date) == monthFormat.format(todayDate))
                thinksLastMonth.add(item)
        }

        var i = 0
        while (i < thinksLastMonth.size) {
            val thinkModel = thinksLastMonth[i]

            val dateTmp = dayFormat.format(thinkModel.date)
            elements.add(Pair(
                dayFormat.format(thinkModel.date).toInt(),
                DateConverter.getStringMonthSimple(monthFormat.format(todayDate))
            ))

            val listTmp = mutableListOf<ThinkModel>()

            listTmp.add(thinkModel)
            dateList.add(thinkModel.date)

            var j = i + 1
            while (j < thinksLastMonth.size) {
                if (dayFormat.format(thinksLastMonth[j].date).equals(dateTmp)) {
                    listTmp.add(thinksLastMonth[j])
                    thinksLastMonth.removeAt(j)
                }
                j++
            }

            lastMonthData.add(listTmp)
            i++
        }

        if (elements[elements.size - 1].first != dayFormat.format(todayDate).toInt()) {
            elements.add(
                Pair(
                    dayFormat.format(todayDate).toInt(),
                    DateConverter.getStringMonthSimple(monthFormat.format(todayDate))
                )
            )

            lastMonthData.add(mutableListOf())
            dateList.add(todayDate)
        }

        if (currentDate < 0 || currentDate >= elements.size)
            currentDate = elements.size - 1

        viewState.showDates(elements)
    }

    fun selectDay(position: Int) {
        currentDate = position

        if (position >= lastMonthData.size || lastMonthData.isEmpty())
            return

        val thinks = lastMonthData[position]
        val elements: MutableList<Pair<String, String>> = mutableListOf()

        for (item in thinks) {
            val timeFormat = SimpleDateFormat("HH:mm")
            val pairThinkItem: Pair<String, String> = Pair(timeFormat.format(item.date), item.situation)
            elements.add(pairThinkItem)
        }

        viewState.showThinkList(elements)
    }
}