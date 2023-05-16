package com.example.geographicatlas.ui.model

import com.example.geographicatlas.data.*

data class CountryModel(

    val area: Double,
    val capital: List<String>?,
    val cca2: String,
    val continents: List<String>,
    val currencies: HashMap<String, Currency>?,
    val flag: Flag,
    val capitalInfo: CapitalInfo,
    val name: Name,
    val population: Long,
    val region: String,
    val maps: Maps,
    var isExpanded: Boolean = false
)