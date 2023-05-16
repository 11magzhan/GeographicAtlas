package com.example.geographicatlas.ui.model

sealed class Item {

    data class Country(val country: CountryModel) : Item()
    data class Header(val continents: String) : Item()
}
