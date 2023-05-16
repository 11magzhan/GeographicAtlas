package com.example.geographicatlas.data

import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("name")
    val name: Name,
    @SerializedName("capital")
    val capital: List<String>?,
    @SerializedName("flags")
    val flag: Flag,
    @SerializedName("population")
    val population: Long,
    @SerializedName("area")
    val area: Double,
    @SerializedName("currencies")
    val currencies: HashMap<String, Currency>?,
//    @SerializedName("subregion")
//    val region: String,
    @SerializedName("continents")
    val continents: List<String>,
    @SerializedName("cca2")
    val cca2: String,
    @SerializedName("capitalInfo")
    val capitalInfo: CapitalInfo,
    @SerializedName("region")
    val region: String,
    @SerializedName("maps")
    val maps: Maps,
//    @SerializedName("timezones")
//    val timezones: List<String>
)

data class Currency(
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String
)

data class Name(
    @SerializedName("common")
    val common: String,
    @SerializedName("official")
    val official: String
)
data class Flag(
    @SerializedName("png")
    val png: String,
)
data class CapitalInfo(

    @SerializedName("latlng")
    val latlng: List<Double>
)

data class Maps(

    @SerializedName("google-maps")
    val googleMaps: String,
    @SerializedName("openstreetmap")
    val openStreetMaps: String
)