package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class SideBarNavigationActivity : AppCompatActivity() {

    lateinit var toggle:ActionBarDrawerToggle

    val drawerLayout by lazy {
        findViewById<DrawerLayout>(R.id.dl_container)
    }

    val navView by lazy {
        findViewById<NavigationView>(R.id.side_navigation_view)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_bar_navigation)

        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)  // back stack and to appear toggle

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> Toast.makeText(this,"Home",Toast.LENGTH_LONG).show()
                R.id.menu_message -> Toast.makeText(this,"Message",Toast.LENGTH_LONG).show()
                R.id.menu_profile -> Toast.makeText(this,"Profile",Toast.LENGTH_LONG).show()
            }
            true
        }




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){

        }
        return true
    }

}