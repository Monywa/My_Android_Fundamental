package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.fragment.HomeFragment
import com.example.myapplication.fragment.MessageFragment
import com.example.myapplication.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavgiationActivity : AppCompatActivity() {

    val bottomNavigation by lazy{
        findViewById<BottomNavigationView>(R.id.bottomNavigationView)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navgiation)
        
        
        val firstFragment=HomeFragment()
        val secondFragment= MessageFragment()
        val thirdFragment=ProfileFragment()

        setCurrentFragmentView(firstFragment)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> setCurrentFragmentView(firstFragment)
                R.id.menu_message -> setCurrentFragmentView(secondFragment)
                R.id.menu_profile-> setCurrentFragmentView(thirdFragment)
            }
            true
        }
        bottomNavigation.getOrCreateBadge(R.id.menu_message).apply {
            number=10
            isVisible=true
        }
    }

    private fun setCurrentFragmentView(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_container,fragment)
            commit()
        }
}