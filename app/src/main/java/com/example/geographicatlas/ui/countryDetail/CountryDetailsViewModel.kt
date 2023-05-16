package com.example.geographicatlas.ui.countryDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.geographicatlas.data.CountryItem
import com.example.geographicatlas.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryDetailsViewModel: ViewModel() {

    val remoteCountry: MutableLiveData<List<CountryItem>> = MutableLiveData()

    fun onViewInit(countryCode: String) {
        getCountryDetails(countryCode)
    }

    private fun getCountryDetails(countryCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            remoteCountry.postValue(RetrofitClient.apiService.getCountry(countryCode))
        }
    }
}