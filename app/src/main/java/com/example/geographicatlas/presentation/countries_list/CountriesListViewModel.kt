package com.example.geographicatlas.presentation.countries_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.remote.RetrofitClient
import com.example.geographicatlas.presentation.model.CountryModel
import com.example.geographicatlas.presentation.model.Item
import com.example.geographicatlas.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesListViewModel : ViewModel() {

    private val _remoteAllCountries: MutableLiveData<Resource<List<Item>>> = MutableLiveData()
    val remoteAllCountries: LiveData<Resource<List<Item>>> get() = _remoteAllCountries

    init {
        Log.d("TAG", "init called")
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _remoteAllCountries.postValue(Resource.Loading())
            val countriesList = RetrofitClient.apiService.getAllCountries()
            val continentGroups = countriesList
                .flatMap { country -> country.continents.map { continent -> continent to country } }
                .groupBy({ it.first }, { it.second })
                .toSortedMap()

            val rowItems = mutableListOf<Item>()

            continentGroups.flatMap { (continent, countryNetworkModels) ->
                listOf(Item.Header(continent)) +
                        countryNetworkModels.map {
                            Item.Country(
                                CountryModel(
                                    area = it.area,
                                    capital = it.capital,
                                    cca2 = it.cca2,
                                    continents = it.continents,
                                    currencies = it.currencies,
                                    flags = it.flags,
                                    capitalInfo = it.capitalInfo,
                                    name = it.name,
                                    population = it.population,
                                    region = it.region,
                                    maps = it.maps
                                )
                            )
                        }
            }.toCollection(rowItems)
            _remoteAllCountries.postValue(Resource.Success(rowItems))
        }
    }
}