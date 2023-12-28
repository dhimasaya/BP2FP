package com.example.bp2fp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        //fragment
        val mainFragment=HomeFragment()
        val journalFragment=JournalFragment<Any>()
        val newsFragment=NewsFragment()
        val sessionFragment=SessionFragment()
        val communityFragment=CommunityFragment()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,mainFragment)
            commit()

        }

        currentFragment(mainFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home->currentFragment(mainFragment)
                R.id.journal->currentFragment(journalFragment)
                R.id.news->currentFragment(newsFragment)
                R.id.session->currentFragment(sessionFragment)
                R.id.community->currentFragment(communityFragment)

            }
            true
        }


    }

    private fun currentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,fragment)
            commit()
        }



}