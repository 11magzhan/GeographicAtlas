package com.example.geographicatlas.api

import com.example.geographicatlas.data.CountryItem
import retrofit2.http.GET

interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): List<CountryItem>
}