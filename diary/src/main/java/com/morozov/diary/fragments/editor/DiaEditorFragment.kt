package com.morozov.diary.fragments.editor

import android.arch.lifecycle.MutableLiveData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.diary.R
import com.morozov.diary.domain.DiaMainView
import com.morozov.diary.domain.models.EmotionModel
import com.morozov.diary.domain.models.ThinkModel
import com.morozov.diary.utility.AppConstants
import com.morozov.diary.utility.DateConverter
import kotlinx.android.synthetic.main.fragment_think_editor.*
import java.text.SimpleDateFormat
import java.util.*

class DiaEditorFragment: MvpAppCompatFragment(), EditorView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: EditorPresenter
    lateinit var mActivityPresenter: DiaMainView

    lateinit var mDate: Date

    /*
    * LiveData
    *
    * */
    var joy = MutableLiveData<Boolean>()
    var sadness = MutableLiveData<Boolean>()
    var annoyance = MutableLiveData<Boolean>()
    var anxiety = MutableLiveData<Boolean>()
    var disgust = MutableLiveData<Boolean>()
    var interest = MutableLiveData<Boolean>()
    var guilt = MutableLiveData<Boolean>()
    var resentment = MutableLiveData<Boolean>()

    /*
    * Emotions
    *
    * */
    var selectedEmotions = arrayListOf<EmotionModel>()
    var currentEmotion= MutableLiveData<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_think_editor, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        buttonDiarySave.setOnClickListener {
            if (bundle != null) {
                if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                    mPresenter.saveNewThink(getThink())
                else
                    mPresenter.saveOldThink(getThink())

                mActivityPresenter.showDiaryCards()
            }
        }

        seekBarDiaryEditor.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textDiaryPercent.text = "$progress%"
                if (currentEmotion.value!! >= 0 && currentEmotion.value!! < selectedEmotions.size) {
                    selectedEmotions[currentEmotion.value!!].percent = seekBarDiaryEditor.progress

                    when (selectedEmotions[currentEmotion.value!!].emotion) {
                        EmotionModel.Emotion.JOY -> textJoy.text = "$progress%"
                        EmotionModel.Emotion.SADNESS -> textSadness.text = "$progress%"
                        EmotionModel.Emotion.ANNOYANCE -> textAnnoyance.text = "$progress%"
                        EmotionModel.Emotion.ANXIETY -> textAnxiety.text = "$progress%"
                        EmotionModel.Emotion.DISGUST -> textDisgust.text = "$progress%"
                        EmotionModel.Emotion.INTEREST -> textInterest.text = "$progress%"
                        EmotionModel.Emotion.GUILT -> textGuilt.text = "$progress%"
                        EmotionModel.Emotion.RESENTMENT -> textResentment.text = "$progress%"
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        currentEmotion.observeForever {
            if (it != null)
            when (it) {
                -1 -> hideSeekBar()
                else -> {
                    showSeekBar()
                }
            }
        }

        currentEmotion.value = -1

        setEmotionsOnClick()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        mDate = if (bundle != null) {
            bundle.getSerializable(AppConstants.DIARY_SELECTED_DAY) as Date
        } else {
            Date()
        }

        if (bundle != null) {
            if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                mPresenter.initNewThink(mDate)
            else
                mPresenter.loadOldThink(mDate)
        }
    }

    /*
    * DiaryEditorView implementation
    *
    * */
    override fun showButtonSave() {
        buttonDiarySave.visibility = View.VISIBLE
    }

    override fun hideButtonSave() {
        buttonDiarySave.visibility = View.GONE
    }

    override fun showSeekBar() {
        seekBarDiaryEditor.progress = 1
        textDiaryPercent.text = "1%"

        textDiaryPercent.visibility = View.VISIBLE
        textDiaryEmotion.visibility = View.VISIBLE
        seekBarDiaryEditor.visibility = View.VISIBLE
    }

    override fun hideSeekBar() {
        textDiaryPercent.visibility = View.INVISIBLE
        textDiaryEmotion.visibility = View.INVISIBLE
        seekBarDiaryEditor.visibility = View.INVISIBLE
    }

    override fun showThink(think: ThinkModel) {
        setDate(think.date)
        editTextDiarySituation.setText(think.situation)
        editTextDiaryThink.setText(think.think)
        editTextDiarySensation.setText(think.sensation)
        showEmotions(think.emotion)
    }

    override fun setDate(date: Date) {
        val dayFormat = SimpleDateFormat("dd")
        val monthFormat = SimpleDateFormat("MM")
        val yearFormat = SimpleDateFormat("yyyy")

        val dateStr =
                    dayFormat.format(date) + " " +
                    DateConverter.getStringMonth(monthFormat.format(date)) + " ," +
                    yearFormat.format(date)

        textDiaryEditorTime.text = dateStr
    }

    private fun getThink(): ThinkModel {
        val bundle = this.arguments

        var date = Date()

        if (bundle != null) {
            date = if (bundle.getBoolean(AppConstants.DIARY_IS_NEW_ITEM))
                mPresenter.dateNew
            else
                mPresenter.dateOld
        }

        mDate.hours = date.hours
        mDate.minutes = date.minutes

        for (item in selectedEmotions) {
            println(item.percent)
        }

        return ThinkModel(mDate, editTextDiarySituation.text.toString(),
            editTextDiaryThink.text.toString(),
            selectedEmotions,
            editTextDiarySensation.text.toString())
    }

    /*
    *  Emotions controll
    *
    *  */
    override fun setIsActiveJoy(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.JOY, getPercent()))
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_joy))
                textJoy.text = "1%"
                textJoy.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.JOY)
                imageJoy.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_joy))
                textJoy.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveSadness(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.SADNESS, getPercent()))
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_sedness))
                textSadness.text = "1%"
                textSadness.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.SADNESS)
                imageSadness.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_sedness))
                textSadness.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveAnnoyance(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.ANNOYANCE, getPercent()))
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_annoyance))
                textAnnoyance.text = "1%"
                textAnnoyance.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANNOYANCE)
                imageAnnoyance.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_annoyance))
                textAnnoyance.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveAnxiety(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.ANXIETY, getPercent()))
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_anxiety))
                textAnxiety.text = "1%"
                textAnxiety.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.ANXIETY)
                imageAnxiety.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_anxiety))
                textAnxiety.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveDisgust(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.DISGUST, getPercent()))
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_disgust))
                textDisgust.text = "1%"
                textDisgust.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.DISGUST)
                imageDisgust.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_disgust))
                textDisgust.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveInterest(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.INTEREST, getPercent()))
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_interest))
                textInterest.text = "1%"
                textInterest.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.INTEREST)
                imageInterest.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_interest))
                textInterest.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveGuilt(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.GUILT, getPercent()))
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_guilt))
                textGuilt.text = "1%"
                textGuilt.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.GUILT)
                imageGuilt.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_guilt))
                textGuilt.visibility = View.GONE
            }
        }
    }

    override fun setIsActiveResentment(b: Boolean) {
        when (b) {
            true -> {
                addEmotion(EmotionModel(EmotionModel.Emotion.RESENTMENT, getPercent()))
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_active_resentment))
                textResentment.text = "1%"
                textResentment.visibility = View.VISIBLE
            }
            false -> {
                removeEmotion(EmotionModel.Emotion.RESENTMENT)
                imageResentment.setImageDrawable(resources.getDrawable(R.drawable.emotion_non_active_resentment))
                textResentment.visibility = View.GONE
            }
        }
    }

    /*
    * Helper functions
    *
    * */
    private fun showEmotions(items: List<EmotionModel>) {
        for (item in items) {
            when (item.emotion) {
                EmotionModel.Emotion.JOY -> {
                    joy.value = true
                    textJoy.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.SADNESS -> {
                    sadness.value = true
                    textSadness.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANNOYANCE -> {
                    annoyance.value = true
                    textAnnoyance.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.ANXIETY -> {
                    anxiety.value = true
                    textAnxiety.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.DISGUST -> {
                    disgust.value = true
                    textDisgust.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.INTEREST -> {
                    interest.value = true
                    textInterest.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.GUILT -> {
                    guilt.value = true
                    textGuilt.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
                EmotionModel.Emotion.RESENTMENT -> {
                    resentment.value = true
                    textResentment.text = item.percent.toString() + "%"
                    seekBarDiaryEditor.progress = item.percent
                    textDiaryPercent.text = item.percent.toString() + "%"
                }
            }
        }
    }

    private fun addEmotion(emotion: EmotionModel) {
        selectedEmotions.add(emotion)
        currentEmotion.value = selectedEmotions.size - 1
    }

    private fun removeEmotion(emotion: EmotionModel.Emotion) {
        val newList = arrayListOf<EmotionModel>()

        for (item in selectedEmotions) {
            if (item.emotion != emotion) {
                newList.add(item)
            } else {
                currentEmotion.value = -1
            }
        }
        selectedEmotions = newList
    }

    private fun getPercent(): Int {
        return 0
    }

    private fun setEmotionsOnClick() {
        setEmotionsObservers()

        imageJoy.setOnClickListener {
            joy.value = !joy.value!!
        }
        imageSadness.setOnClickListener {
            sadness.value = !sadness.value!!
        }
        imageAnnoyance.setOnClickListener {
            annoyance.value = !annoyance.value!!
        }
        imageAnxiety.setOnClickListener {
            anxiety.value = !anxiety.value!!
        }
        imageDisgust.setOnClickListener {
            disgust.value = !disgust.value!!
        }
        imageInterest.setOnClickListener {
            interest.value = !interest.value!!
        }
        imageGuilt.setOnClickListener {
            guilt.value = !guilt.value!!
        }
        imageResentment.setOnClickListener {
            resentment.value = !resentment.value!!
        }
    }

    private fun setEmotionsObservers() {
        joy.observeForever {
            if (it != null)
                setIsActiveJoy(it)
        }
        sadness.observeForever {
            if (it != null)
                setIsActiveSadness(it)
        }
        annoyance.observeForever {
            if (it != null)
                setIsActiveAnnoyance(it)
        }
        anxiety.observeForever {
            if (it != null)
                setIsActiveAnxiety(it)
        }
        disgust.observeForever {
            if (it != null)
                setIsActiveDisgust(it)
        }
        interest.observeForever {
            if (it != null)
                setIsActiveInterest(it)
        }
        guilt.observeForever {
            if (it != null)
                setIsActiveGuilt(it)
        }
        resentment.observeForever {
            if (it != null)
                setIsActiveResentment(it)
        }

        joy.value = false
        sadness.value = false
        annoyance.value = false
        anxiety.value = false
        disgust.value = false
        interest.value = false
        guilt.value = false
        resentment.value = false
    }
}