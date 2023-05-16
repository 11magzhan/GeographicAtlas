package com.example.geographicatlas.ui.model

import com.example.geographicatlas.data.CountryItem

sealed class Item {

    data class Country(val country: CountryItem) : Item()
    data class Header(val continents: String) : Item()
}
