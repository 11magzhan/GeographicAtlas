package com.example.geographicatlas

import retrofit2.http.GET

interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): List<CountryItem>
}