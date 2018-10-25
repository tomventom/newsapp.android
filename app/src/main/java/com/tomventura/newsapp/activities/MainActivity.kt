package com.tomventura.newsapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tomventura.newsapp.R
import com.tomventura.newsapp.fragments.NewsFragment
import com.tomventura.newsapp.util.changeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.changeFragment(NewsFragment())
        }
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
