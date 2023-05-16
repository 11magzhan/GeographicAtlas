package com.example.geographicatlas.data.remote

import com.example.geographicatlas.data.CountryItem
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesAPI {

    @GET("all")
    suspend fun getAllCountries(): List<CountryItem>

    @GET("alpha/{code}")
    suspend fun getCountry(@Path("name") code: String): CountryItem
}