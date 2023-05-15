package com.example.geographicatlas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.geographicatlas.ui.countriesList.CountriesListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(CountriesListFragment())
    }
}