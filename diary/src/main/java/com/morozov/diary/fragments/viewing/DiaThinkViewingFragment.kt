package com.morozov.diary.fragments.viewing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.diary.R
import com.morozov.diary.domain.DiaMainView
import com.morozov.diary.domain.models.EmotionModel
import com.morozov.diary.domain.models.ThinkModel
import com.morozov.diary.utility.DiaConstants
import com.morozov.diary.utility.DateConverter
import kotlinx.android.synthetic.main.fragment_think_viewing.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DiaThinkViewingFragment: MvpAppCompatFragment(), ThinkViewingView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ThinkViewingPresenter
    lateinit var mActivityPresenter: DiaMainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_think_viewing, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments
        if (bundle != null)
            buttonDiaryEditThink.setOnClickListener {
                mActivityPresenter.showDiaryEditor(false,
                    bundle.getSerializable(DiaConstants.DIARY_SELECTED_DAY) as Date)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null)
            mPresenter.showThink(bundle.getSerializable(DiaConstants.DIARY_SELECTED_DAY) as Date)
    }

    /*
        * DiaryThinkViewingView implementation
        *
        * */
    override fun showThink(think: ThinkModel) {
        setDate(think.date)
        textDiaryViewSituation.text = think.situation
        textDiaryViewThink.text = think.think
        textDiaryViewSensation.text = think.sensation
        showEmotions(think.emotion)
    }

    /*
    * Helper functions
    *
    * */
    private fun setDate(date: Date) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")

        val dateStr =
            dayFormat.format(date) + " " +
                    DateConverter.getStringMonth(monthFormat.format(date)) + " ," +
                    yearFormat.format(date)

        textDiaryViewingTime.text = dateStr
    }

    private fun showEmotions(emotions: ArrayList<EmotionModel>) {
        for (item in emotions) {
            when (item.emotion) {
                EmotionModel.Emotion.JOY -> {
                    imageJoyViewing.visibility = View.VISIBLE
                    textJoyViewing.text = item.percent.toString() + "%"
                    textJoyViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.SADNESS -> {
                    imageSadnessViewing.visibility = View.VISIBLE
                    textSadnessViewing.text = item.percent.toString() + "%"
                    textSadnessViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.ANNOYANCE -> {
                    imageAnnoyanceViewing.visibility = View.VISIBLE
                    textAnnoyanceViewing.text = item.percent.toString() + "%"
                    textAnnoyanceViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.ANXIETY -> {
                    imageAnxietyViewing.visibility = View.VISIBLE
                    textAnxietyViewing.text = item.percent.toString() + "%"
                    textAnxietyViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.DISGUST -> {
                    imageDisgustViewing.visibility = View.VISIBLE
                    textDisgustViewing.text = item.percent.toString() + "%"
                    textDisgustViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.INTEREST -> {
                    imageInterestViewing.visibility = View.VISIBLE
                    textInterestViewing.text = item.percent.toString() + "%"
                    textInterestViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.GUILT -> {
                    imageGuiltViewing.visibility = View.VISIBLE
                    textGuiltViewing.text = item.percent.toString() + "%"
                    textGuiltViewing.visibility = View.VISIBLE
                }
                EmotionModel.Emotion.RESENTMENT -> {
                    imageResentmentViewing.visibility = View.VISIBLE
                    textResentmentViewing.text = item.percent.toString() + "%"
                    textResentmentViewing.visibility = View.VISIBLE
                }
            }
        }
    }
}