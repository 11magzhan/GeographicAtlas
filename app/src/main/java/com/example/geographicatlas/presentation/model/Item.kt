package com.example.geographicatlas.presentation.model

sealed class Item {
    data class Country(val country: CountryModel) : Item()
    data class Header(val continents: String) : Item()
}