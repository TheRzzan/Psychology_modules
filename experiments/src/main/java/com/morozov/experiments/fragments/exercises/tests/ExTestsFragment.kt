package com.morozov.experiments.fragments.exercises.tests

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.adapters.listeners.OnTextChangeListener
import com.morozov.experiments.adapters.test.TestAdapter
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.utility.AppConstants
import kotlinx.android.synthetic.main.fragment_test.*

class ExTestsFragment: MvpAppCompatFragment(), TestsView, OnTextChangeListener {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: TestsPresenter
    lateinit var mActivityPresenter: ExpMainView

    var allTextRecycler: MutableList<Boolean> = mutableListOf()

    /*
    * Recycler adapterTest
    *
    * */
    lateinit var adapter: TestAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        adapter = TestAdapter(this)
        recyclerTest.layoutManager = LinearLayoutManager(context)
        recyclerTest.adapter = adapter

        buttonFinishTest.setOnClickListener {
            if (mActivityPresenter != null) {
                if (bundle != null)
                    mActivityPresenter.showExResults(bundle.getInt(AppConstants.EXP_POSITION))
                else
                    mActivityPresenter.showExResults(0)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.loadData(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mPresenter.loadData(0)
    }

    /*
    * OnTextChangeListener implementation
    *
    * */
    override fun onTextChanged(position: Int, count: Int, symbolSet: String) {
        allTextRecycler[position] = count > 0

        var b = true
        for (item in allTextRecycler) {
            if (!item) {
                b = false
                break
            }
        }

        setFinishEnabled(b)
    }

    /*
    * ExTestsView implementation
    *
    * */
    override fun showVariants(variants: List<String>) {
        adapter.setData(variants)
        allTextRecycler = mutableListOf()

        var i = 0
        while (i < adapter.itemCount) {
            i++
            allTextRecycler.add(false)
        }

        setFinishEnabled(false)
    }

    override fun showTitle(title: String) {
        if (title == "")
            textTestName.visibility = View.GONE
        else
            textTestName.text = title
    }

    override fun showQuestion(description: String) {
        if (description == "")
            textTestDescr.visibility = View.GONE
        else
            textTestDescr.text = description
    }

    override fun setFinishEnabled(boolean: Boolean) {
        when(boolean) {
            true -> buttonFinishTest.setBackgroundResource(R.drawable.rectangle_button)
            false -> buttonFinishTest.setBackgroundResource(R.drawable.rectangle_button_disable)
        }
        buttonFinishTest.isEnabled = boolean
    }
}