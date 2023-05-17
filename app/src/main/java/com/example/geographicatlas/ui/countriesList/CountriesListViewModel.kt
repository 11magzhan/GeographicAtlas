package com.example.geographicatlas.ui.countriesList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.remote.RetrofitClient
import com.example.geographicatlas.ui.model.CountryModel
import com.example.geographicatlas.ui.model.Item
import com.example.geographicatlas.utilities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountriesListViewModel: ViewModel()  {

    val remoteAllCountries: MutableLiveData<Resource<List<Item>>> = MutableLiveData()

    init {
        Log.d("TAG", "init called")
        getAllCountries()
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            remoteAllCountries.postValue(Resource.Loading())
        Log.d("TAG", "getallcountries scope called")
            // take all data for group
            val countriesList = RetrofitClient.apiService.getAllCountries()
            // map to list of Country class
            val continentGroups = countriesList
                .flatMap { country -> country.continents.map { continent -> continent to country } }
                .groupBy({ it.first }, { it.second })
                .toSortedMap()

            // group by continent
//            val groupedCountries = countriesList.groupBy { it.continents }

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
                                    flag = it.flag,
                                    capitalInfo = it.capitalInfo,
                                    name = it.name,
                                    population = it.population,
                                    region = it.region,
                                    maps = it.maps
                                )
                            )
                        }
            }.toCollection(rowItems)

        Log.d("TAG", "getallcountries scope finishing")
            remoteAllCountries.postValue(Resource.Success(rowItems))
        }
    }
}