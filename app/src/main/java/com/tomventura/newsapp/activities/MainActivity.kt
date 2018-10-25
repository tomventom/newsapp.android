package com.tomventura.newsapp.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tomventura.newsapp.R
import com.tomventura.newsapp.fragments.HeadlinesFragment
import com.tomventura.newsapp.fragments.NewsFragment
import com.tomventura.newsapp.util.changeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newsFragment = NewsFragment()
    private val headlinesFragment = HeadlinesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            bottomNavigationView.selectedItemId = R.id.tab_news
        }
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.tab_news -> {
                supportFragmentManager.changeFragment(newsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_headlines -> {
                supportFragmentManager.changeFragment(headlinesFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.tab_profile -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
