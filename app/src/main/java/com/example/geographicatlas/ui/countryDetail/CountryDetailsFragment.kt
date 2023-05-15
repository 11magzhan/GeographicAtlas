package com.example.geographicatlas.ui.countryDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding
import com.example.geographicatlas.ui.countriesList.CountriesListViewModel

class CountryDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCountryDetailsBinding
    private val viewModel: CountryDetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCountryDetailsBinding.bind(view)
    }
}