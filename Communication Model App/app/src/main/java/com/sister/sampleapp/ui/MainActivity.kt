package com.sister.sampleapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sister.sampleapp.R
import com.sister.sampleapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.bottomNavigation
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.home_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            navView.visibility =
                if (destination.id == R.id.messageRecieverFragment) View.GONE else View.VISIBLE
        }
    }
}