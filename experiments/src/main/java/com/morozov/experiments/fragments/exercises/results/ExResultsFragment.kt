package com.morozov.experiments.fragments.exercises.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.utility.AppConstants
import kotlinx.android.synthetic.main.fragment_results.*

class ExResultsFragment: MvpAppCompatFragment(), ResultsView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: ResultsPresenter
    lateinit var mActivityPresenter: ExpMainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_results, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonChoseAnother.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExpCards()
        }

        buttonConsolidateScill.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExFixDescr(0)
        }
    }

    override fun onStart() {
        super.onStart()

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.loadResult(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mPresenter.loadResult(0)
    }

    /*
    * ExResultsView implementation
    *
    * */
    override fun showTitle(title: String) {
        textResultName.text = title
    }

    override fun showResult(result: String) {
        textResultDescription.text = result
    }

    override fun showButtonNext(text: String, position: Int) {
        buttonNextTest.visibility = View.VISIBLE
        buttonNextTest.text = text
        buttonNextTest.setOnClickListener {
            if (mActivityPresenter != null)
                mActivityPresenter.showExTest(position)
        }
    }

    override fun hideButtonNext() {
        buttonNextTest.visibility = View.GONE
    }
}