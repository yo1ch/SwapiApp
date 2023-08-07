package com.example.swapiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.feature.presentation.favoritefragment.FavoriteFragment
import com.example.feature.presentation.mainfragment.MainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        setCurrentFragment(MainFragment())
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mainFragment -> setCurrentFragment(MainFragment())
                R.id.favoriteFragment -> setCurrentFragment(FavoriteFragment())
            }
            true
        }


    }
    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    override fun onStart() {
        super.onStart()
        SwapiApplication.initialize(this)
    }
}
