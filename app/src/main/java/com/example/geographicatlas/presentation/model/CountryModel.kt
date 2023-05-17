package com.example.geographicatlas.presentation.model

import com.example.geographicatlas.data.remote.model.*

data class CountryModel(
    val area: Double,
    val capital: List<String>?,
    val cca2: String,
    val continents: List<String>,
    val currencies: HashMap<String, Currency>?,
    val flags: Flag,
    val capitalInfo: CapitalInfo,
    val name: Name,
    val population: Long,
    val region: String,
    val maps: Maps,
    var isExpanded: Boolean = false
)