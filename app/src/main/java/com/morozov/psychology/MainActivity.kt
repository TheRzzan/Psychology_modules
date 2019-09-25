package com.morozov.psychology

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.fragments.cards.CardsFragment
import com.morozov.experiments.fragments.exercises.desrciption.ExDescriptionFragment
import com.morozov.experiments.fragments.exercises.results.ExResultsFragment
import com.morozov.experiments.fragments.exercises.tests.ExTestsFragment
import com.morozov.experiments.fragments.fix.desrciption.FixDescriptionFragment
import com.morozov.experiments.fragments.fix.results.FixResultsFragment
import com.morozov.experiments.fragments.fix.tests.FixTestsFragment
import com.morozov.experiments.utility.AppConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ExpMainView {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_experiments -> {
                showExpCards()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tests -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_diary -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_change -> {
                //
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profile -> {
                //
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showExpCards()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        linearBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        when (supportFragmentManager.backStackEntryCount) {
            0 -> super.onBackPressed()
            1 -> {
                showBottomNav()
                hideBackArrow()
                supportFragmentManager.popBackStack()
            }
            else -> {
                val fragment = supportFragmentManager.fragments[supportFragmentManager.fragments.size - 1]
                if (fragment is ExTestsFragment ||
                    fragment is FixTestsFragment) {
                    hideBackArrow()
                }

                supportFragmentManager.popBackStack()
            }
        }
    }

    /*
    * Interface controls
    *
    * */
    fun showBottomNav() {
        navigation.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        navigation.visibility = View.GONE
    }

    fun showBackArrow() {
        linearBack.visibility = View.VISIBLE
    }

    fun hideBackArrow() {
        linearBack.visibility = View.GONE
    }

    /*
    * ExpMainView implementation
    *
    * */
    override fun showExpCards() {
        showBottomNav()
        hideBackArrow()

        val fragment = CardsFragment()
        fragment.mActivityPresenter = this

        clearBackStack()
        setFragment(fragment)
    }

    override fun showExDescr(position: Int) {
        hideBottomNav()
        hideBackArrow()

        val exDescriptionFragment = ExDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exDescriptionFragment.arguments = bundle
        exDescriptionFragment.mActivityPresenter = this

        clearBackStack()
        setFragment(exDescriptionFragment, true)
    }

    override fun showExFixDescr(position: Int) {
        hideBottomNav()
        hideBackArrow()

        val exFixDescriptionFragment = FixDescriptionFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exFixDescriptionFragment.arguments = bundle
        exFixDescriptionFragment.mActivityPresenter = this

        clearBackStack()
        setFragment(exFixDescriptionFragment, true)
    }

    override fun showExTest(position: Int) {
        hideBottomNav()
        showBackArrow()

        val exTestsFragment = ExTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exTestsFragment.arguments = bundle
        exTestsFragment.mActivityPresenter = this

        setFragment(exTestsFragment, true)
    }

    override fun showExFixTest(position: Int) {
        hideBottomNav()
        showBackArrow()

        val exFixTestsFragment = FixTestsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exFixTestsFragment.arguments = bundle
        exFixTestsFragment.mActivityPresenter = this

        setFragment(exFixTestsFragment, true)
    }

    override fun showExResults(position: Int) {
        hideBottomNav()
        showBackArrow()

        val exResultsFragment = ExResultsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exResultsFragment.arguments = bundle
        exResultsFragment.mActivityPresenter = this

        setFragment(exResultsFragment, true)
    }

    override fun showExFixResults(position: Int) {
        hideBottomNav()
        showBackArrow()

        val exfixResultsFragment = FixResultsFragment()

        val bundle = Bundle()
        bundle.putInt(AppConstants.EXP_POSITION, position)

        exfixResultsFragment.arguments = bundle
        exfixResultsFragment.mActivityPresenter = this

        setFragment(exfixResultsFragment, true)
    }

    override fun showDiaryCards() {

    }

    /*
    *  Helper methods
    *
    *  */
    private fun setFragment(fragment: Fragment, b: Boolean = false) {
        val transaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.contentMain, fragment)

        if (b)
            transaction.addToBackStack(null)

        transaction.commit()
    }

    private fun clearBackStack() {
        var i = 0
        while (i < supportFragmentManager.backStackEntryCount){
            i++
            supportFragmentManager.popBackStack()
        }
    }
}
