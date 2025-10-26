package com.ramazan.testproject.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ramazan.testproject.R
import com.ramazan.testproject.view.fragment.FavoritesFragment
import com.ramazan.testproject.view.fragment.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, HomeFragment())
            .commit()

        val navBar = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navBar.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.homeFragment -> HomeFragment()
                R.id.favoritesFragment -> FavoritesFragment()
                else -> null
            }
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, it)
                    .commit()
            }
            true
        }
    }
}