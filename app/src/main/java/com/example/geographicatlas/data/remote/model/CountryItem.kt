package com.example.geographicatlas.data

import com.example.geographicatlas.data.remote.model.*

data class CountryItem(
    val name: Name,
    val capital: List<String>?,
    val flags: Flag,
    val population: Long,
    val area: Double,
    val currencies: HashMap<String, Currency>?,
    val continents: List<String>,
    val capitalInfo: CapitalInfo,
    val region: String,
    val maps: Maps,
    val subregion: String?,
    val cca2: String,
    val timezones: List<String>
)







