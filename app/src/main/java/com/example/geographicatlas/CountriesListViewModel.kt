package com.example.geographicatlas

import androidx.lifecycle.ViewModel

class CountriesListViewModel: ViewModel()  {
    var countries = mutableListOf(
        CountryItem("Kazakhstan", "Astana", "${R.drawable.kz}", "19mln", 234.0),
        CountryItem("Argentina", "Buenos", "${R.drawable.kz}", "191mln", 180.0),
        CountryItem("Germany", "Berlin", "${R.drawable.kz}", "83mln", 142.0)
    )
}