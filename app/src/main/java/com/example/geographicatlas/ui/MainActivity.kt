package com.example.geographicatlas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // replaceFragment(CountriesListFragment())

        val navHostFragment = supportFragmentManager.
        findFragmentById(R.id.container) as NavHostFragment
        navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.countriesListFragment,
                R.id.countryDetailsFragment,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}