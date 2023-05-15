package com.example.geographicatlas

import com.google.gson.annotations.SerializedName

data class CountryItem(
//    @SerializedName("name")
//    val name: String,
//    @SerializedName("capital")
//    val capital: String,
//    @SerializedName("flag")
//    val flag: String,
    @SerializedName("population")
    val population: Long,
    @SerializedName("area")
    val area: Double,
//    @SerializedName("currencies")
//    val currencies: List<Currency>,
    @SerializedName("region")
    val region: String,
//    @SerializedName("timezones")
//    val timezones: List<String>
)