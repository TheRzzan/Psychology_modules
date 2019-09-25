package com.morozov.experiments.fragments.fix.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.domain.ExpMainView
import kotlinx.android.synthetic.main.fragment_fix_results.*

class FixResultsFragment: MvpAppCompatFragment(), ResultsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ResultsPresenter
    lateinit var mActivityPresenter: ExpMainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fix_results, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFixDiary.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showDiaryCards()
        }

        buttonFixChooseExperiment.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExpCards()
        }
    }

    /*
    * ExResultsView implementation
    *
    * */
    override fun showTitle(title: String) {
        textFixResultName.text = title
    }

    override fun showResult(result: String) {
        textFixResultDescription.text = result
    }

    override fun showButtonNext(text: String, position: Int) {
        buttonFixNext.visibility = View.VISIBLE
        buttonFixNext.text = text
        buttonFixNext.setOnClickListener {

        }
    }

    override fun hideButtonNext() {
        buttonFixNext.visibility = View.GONE
    }
}