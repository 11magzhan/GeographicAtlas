package com.example.geographicatlas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geographicatlas.R
import com.example.geographicatlas.replaceFragment
import com.example.geographicatlas.ui.countriesList.CountriesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(CountriesListFragment())
    }
}