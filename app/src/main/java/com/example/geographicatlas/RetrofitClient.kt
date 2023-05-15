package com.example.geographicatlas

import com.example.geographicatlas.api.CountriesAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://restcountries.com/v3.1/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: CountriesAPI by lazy {
        retrofit.create(CountriesAPI::class.java)
    }
}
