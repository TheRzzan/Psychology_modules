package com.morozov.experiments.fragments.fix.tests

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.adapters.listeners.OnTextChangeListener
import com.morozov.experiments.adapters.results.ResultsAdapter
import com.morozov.experiments.adapters.test.TestAdapter
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.fragments.cards.mvp.model.ExFixingResultModel
import com.morozov.experiments.utility.AppConstants
import kotlinx.android.synthetic.main.fragment_fix_test.*

class FixTestsFragment: MvpAppCompatFragment(), TestsView, OnTextChangeListener {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: TestsPresenter
    lateinit var mActivityPresenter: ExpMainView

    var allTextRecycler: MutableList<String> = mutableListOf()

    var currentPos = 0

    /*
    * Recycler adapterTest
    *
    * */
    lateinit var adapterTest: TestAdapter
    lateinit var adapterResults: ResultsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fix_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        adapterTest = TestAdapter(this)
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapterTest

        buttonFixFinishTest.setOnClickListener {
            if (bundle != null)
                mPresenter.showResults(bundle.getInt(AppConstants.EXP_POSITION),currentPos, allTextRecycler)
            else
                mPresenter.showResults(0, currentPos, allTextRecycler)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.loadData(bundle.getInt(AppConstants.EXP_POSITION), currentPos)
        else
            mPresenter.loadData(0, currentPos)
    }

    /*
    * OnTextChangeListener implementation
    *
    * */
    override fun onTextChanged(position: Int, count: Int, symbolSet: String) {
        allTextRecycler[position] = symbolSet

        var b = true
        for (item in allTextRecycler) {
            if (item.isEmpty()) {
                b = false
                break
            }
        }

        setFinishEnabled(b)
    }

    /*
    * ExFixTestsView implementation
    *
    * */
    override fun showData(description: String, data: List<String>) {
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapterTest
        adapterTest.setData(data)

        allTextRecycler = mutableListOf()

        textFixTestDescr.text = description

        val bundle = this.arguments
        buttonFixFinishTest.setOnClickListener {
            if (bundle != null)
                mPresenter.showResults(bundle.getInt(AppConstants.EXP_POSITION),currentPos, allTextRecycler)
            else
                mPresenter.showResults(0, currentPos, allTextRecycler)
        }

        var i = 0
        while (i < adapterTest.itemCount) {
            i++
            allTextRecycler.add("")
        }

        setFinishEnabled(false)

        linearFixTestHint.visibility = View.GONE
        textFixTestHint.visibility = View.VISIBLE
    }

    override fun showResults(data: List<ExFixingResultModel>) {
        adapterResults = ResultsAdapter()
        recyclerFixTest.layoutManager = LinearLayoutManager(context)
        recyclerFixTest.adapter = adapterResults

        adapterResults.setData(data)

        scrollFixTest.smoothScrollTo(0, 0)

        buttonFixFinishTest.setOnClickListener {
            scrollFixTest.scrollTo(0, 0)

            val bundle = this.arguments
            currentPos++

            if (bundle != null)
                mPresenter.loadData(bundle.getInt(AppConstants.EXP_POSITION), currentPos)
            else
                mPresenter.loadData(0, currentPos)
        }

        linearFixTestHint.visibility = View.VISIBLE
        textFixTestHint.visibility = View.GONE
    }

    override fun setFinishEnabled(boolean: Boolean) {
        when(boolean) {
            true -> buttonFixFinishTest.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonFixFinishTest.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonFixFinishTest.isEnabled = boolean
    }

    override fun outOfTest() {
        val bundle = this.arguments

        if (bundle != null)
            mActivityPresenter.showExFixResults(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mActivityPresenter.showExFixResults(0)
    }

    override fun setButtonText(text: String) {
        buttonFixFinishTest.text = text
    }
}