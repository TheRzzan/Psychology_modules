package com.morozov.experiments.fragments.cards

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.adapters.cards.CardsAdapter
import com.morozov.experiments.adapters.cards.FixCardsAdapter
import com.morozov.experiments.adapters.listeners.OnItemClickListener
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.fragments.cards.mvp.presenter.CardsPresenter
import com.morozov.experiments.fragments.cards.mvp.view.CardsView
import kotlinx.android.synthetic.main.fragment_cards.*

class ExCardsFragment: MvpAppCompatFragment(), CardsView, OnItemClickListener {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: CardsPresenter
    lateinit var mActivityPresenter: ExpMainView

    /*
    * Recycler adapters
    *
    * */
    lateinit var adapterExp: CardsAdapter
    lateinit var adapterFix: FixCardsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_cards, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterExp = CardsAdapter(this)
        adapterFix = FixCardsAdapter(this)

        recyclerCardsExper.layoutManager = LinearLayoutManager(context)
        recyclerCardsExper.adapter = adapterExp

        recyclerCardsFixing.layoutManager = LinearLayoutManager(context)
        recyclerCardsFixing.adapter = adapterFix
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPresenter.loadDataExperiments()
        mPresenter.loadDataFixing()
    }

    /*
    * OnItemClickListener implementation
    *
    * */
    override fun onItemClick(view: View, position: Int) {
        if (view.id == R.id.imageCard && mActivityPresenter != null) {
            mActivityPresenter.showExDescr(position)
        }

        if (view.id == R.id.imageCardFixing && mActivityPresenter != null) {
            mActivityPresenter.showExFixDescr(position)
        }
    }

    /*
    *ExCardsView implementation
    *
    * */
    override fun showDataExperiments(data: List<String>) {
        adapterExp.setData(data)
    }

    override fun showDataFixing(data: List<String>) {
        adapterFix.setData(data)
    }
}