package com.morozov.experiments.fragments.fix.desrciption

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.morozov.experiments.R
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.utility.AppConstants
import kotlinx.android.synthetic.main.fragment_fix_description.*

class FixDescriptionFragment: MvpAppCompatFragment(), DescriptionView {

    /*
    * Moxy presenters
    *
    * */
    @InjectPresenter
    lateinit var mPresenter: DescriptionPresenter
    lateinit var mActivityPresenter: ExpMainView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_fix_description, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments

        if (bundle != null)
            mPresenter.showFixing(bundle.getInt(AppConstants.EXP_POSITION))
        else
            mPresenter.showFixing(0)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = this.arguments

        buttonFixExit.setOnClickListener {
            activity?.onBackPressed()
        }

        buttonFixStartTest.setOnClickListener {
            if (mActivityPresenter != null) {
                if (bundle != null)
                    mActivityPresenter.showExFixTest(bundle.getInt(AppConstants.EXP_POSITION))
                else
                    mActivityPresenter.showExFixTest(0)
            }
        }
    }

    /*
    * ExDescriptionView implementation
    *
    * */
    override fun showData(name: String, description: String) {
        textFixDescrName.text = name
        textFixDescription.text = description
    }
}