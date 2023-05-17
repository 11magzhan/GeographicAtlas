package com.example.geographicatlas.ui.countryDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.CountryItem
import com.example.geographicatlas.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDetailsViewModel : ViewModel() {

    private val _remoteCountry: MutableLiveData<List<CountryItem>> = MutableLiveData()
    val remoteCountry: LiveData<List<CountryItem>> get() = _remoteCountry

    fun onViewInit(countryCode: String) {
        getCountryDetails(countryCode)
    }

    private fun getCountryDetails(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val country = RetrofitClient.apiService.getCountry(countryCode)
            _remoteCountry.postValue(country)
        }
    }
}