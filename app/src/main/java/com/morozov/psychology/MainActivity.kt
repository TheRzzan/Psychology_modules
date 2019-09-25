package com.morozov.psychology

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.morozov.experiments.domain.ExpMainView
import com.morozov.experiments.fragments.cards.CardsFragment
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
    }

    /*
    * ExpMainView implementation
    *
    * */
    override fun showExpCards() {
        val fragment = CardsFragment()
        fragment.mActivityPresenter = this

        clearBackStack()
        setFragment(fragment)
    }

    override fun showExDescr(position: Int) {

    }

    override fun showExFixDescr(position: Int) {

    }

    override fun showExTest(position: Int) {

    }

    override fun showExFixTest(position: Int) {

    }

    override fun showExResults(position: Int) {

    }

    override fun showExFixResults(position: Int) {

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
