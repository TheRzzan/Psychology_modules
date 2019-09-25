package com.morozov.diary.domain.data

import com.morozov.diary.domain.models.EmotionModel
import com.morozov.diary.domain.models.ThinkModel
import java.text.SimpleDateFormat
import java.util.*

class ThinkLoaderImpl: ThinkLoader, ThinkSaver {

    companion object {
        var dataList: MutableList<ThinkModel> = mutableListOf()
    }

    init {
        if (dataList.isEmpty()) {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")

            val date1 = simpleDateFormat.parse("15/09/2019 08:00")
            val date2 = simpleDateFormat.parse("16/09/2019 08:10")
            val date3 = simpleDateFormat.parse("17/09/2019 09:15")
            val date4 = simpleDateFormat.parse("18/09/2019 16:50")
            val date5 = simpleDateFormat.parse("18/09/2019 00:00")

            val thModel1 = ThinkModel(
                date1,
                "Ситуация 1", "Мысль 1", arrayListOf(EmotionModel(EmotionModel.Emotion.JOY, 100)), "Озарение 1"
            )

            val thModel2 = ThinkModel(
                date2,
                "Ситуация 2", "Мысль 2", arrayListOf(EmotionModel(EmotionModel.Emotion.RESENTMENT, 100)), "Озарение 2"
            )

            val thModel3 = ThinkModel(
                date3,
                "Ситуация 3", "Мысль 3", arrayListOf(EmotionModel(EmotionModel.Emotion.GUILT, 100)), "Озарение 3"
            )

            val thModel4 = ThinkModel(
                date4,
                "Ситуация 4", "Мысль 4", arrayListOf(EmotionModel(EmotionModel.Emotion.JOY, 100)), "Озарение 4"
            )

            val thModel5 = ThinkModel(
                date5,
                "Ситуация 5", "Мысль 5", arrayListOf(EmotionModel(EmotionModel.Emotion.ANXIETY, 100)), "Озарение 5"
            )

            dataList.addAll(listOf(thModel1, thModel2, thModel3, thModel4, thModel5))
        }
    }

    override fun getThinks(): List<ThinkModel> = sordThinksByDate(dataList)

    override fun getThinkByDate(date: Date): ThinkModel? {
        for (item in dataList)
            if (item.date.compareTo(date) == 0)
                return item

        return null
    }

    fun sordThinksByDate(data: MutableList<ThinkModel>): MutableList<ThinkModel> {
        data.sort()
        return data
    }

    override fun saveNew(think: ThinkModel) {
        dataList.add(think)
    }

    override fun overwriteThink(think: ThinkModel) {
        val smpDtFrm = SimpleDateFormat("dd/MM/yyyy HH:mm")

        for (thinkTmp in dataList) {
            if (smpDtFrm.format(think.date) == smpDtFrm.format(thinkTmp.date)) {

                dataList.remove(thinkTmp)
                dataList.add(think)

                break
            }
        }
    }
}