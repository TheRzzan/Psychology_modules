package com.morozov.diary.fragments.cards

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.diary.R
import com.morozov.diary.adapters.date.DiaryDateAdapter
import com.morozov.diary.adapters.date.DiaryDateViewHolder
import com.morozov.diary.adapters.listeners.OnItemClickListener
import com.morozov.diary.adapters.think.list.DiaryThinkAdapter
import com.morozov.diary.domain.DiaMainView
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_cards.*

class DiaCardsFragment:
    MvpAppCompatFragment(), CardsView,
    DiscreteScrollView.OnItemChangedListener<DiaryDateViewHolder>, OnItemClickListener {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: CardsPresenter
    lateinit var mActivityPresenter: DiaMainView

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterDate: DiaryDateAdapter
    lateinit var adapterThink: DiaryThinkAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_cards, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterDate = DiaryDateAdapter()
        recyclerDiaryDays.setSlideOnFling(true)
        recyclerDiaryDays.adapter = adapterDate
        recyclerDiaryDays.addOnItemChangedListener(this)
        recyclerDiaryDays.setItemTransformer(
            ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build())

        adapterThink = DiaryThinkAdapter(this)
        recyclerDiaryThinks.layoutManager = LinearLayoutManager(context)
        recyclerDiaryThinks.adapter = adapterThink

        buttonDiaryAdd.setOnClickListener {
            mActivityPresenter.showDiaryEditor(true, mPresenter.dateList[recyclerDiaryDays.currentItem])
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadData()
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        mActivityPresenter.showDiaryViewing(
            mPresenter.lastMonthData[recyclerDiaryDays.currentItem][position].date
        )
    }

    /*
    * DiaryView implementation
    *
    * */
    override fun showIsEmptyMessage(b: Boolean) {
        when (!b) {
            true -> {
                textDiaryEmptyDay.visibility = View.GONE
                arrowEmpty.visibility = View.GONE
            }
            false -> {
                textDiaryEmptyDay.visibility = View.VISIBLE
                arrowEmpty.visibility = View.VISIBLE
            }
        }
    }

    override fun showDates(elements: List<Pair<Int, String>>) {
        adapterDate.setData(elements)
        adapterDate.notifyDataSetChanged()
        recyclerDiaryDays.scrollToPosition(CardsPresenter.currentDate)
    }

    override fun showThinkList(elements: List<Pair<String, String>>) {
        showIsEmptyMessage(elements.isEmpty())

        adapterThink.setData(elements)
        adapterThink.notifyDataSetChanged()
        if (elements.isNotEmpty())
            recyclerDiaryThinks.scrollToPosition(0)
    }

    /*
    * OnItemChangedListener implementation
    *
    * */
    override fun onCurrentItemChanged(viewHolder: DiaryDateViewHolder?, adapterPosition: Int) {
        mPresenter.selectDay(adapterPosition)
    }
}