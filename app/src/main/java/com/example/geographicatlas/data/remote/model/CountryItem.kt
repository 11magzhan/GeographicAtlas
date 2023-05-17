package com.example.geographicatlas.data

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

data class Currency(
    val name: String,
    val symbol: String
)

data class Name(
    val common: String,
    val official: String
)
data class Flag(
    val png: String,
)
data class CapitalInfo(
    val latlng: List<Double>
)

data class Maps(
    val googleMaps: String,
    val openStreetMaps: String
)