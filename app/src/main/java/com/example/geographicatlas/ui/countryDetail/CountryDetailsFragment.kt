package com.example.geographicatlas.ui.countryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.geographicatlas.data.CountryItem
import com.example.geographicatlas.databinding.FragmentCountryDetailsBinding

class CountryDetailsFragment : Fragment() {

    private var _binding: FragmentCountryDetailsBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<CountryDetailsFragmentArgs>()
    private val viewModel: CountryDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()
        viewModel.onViewInit(args.countryId)
        viewModel.remoteCountry.observe(viewLifecycleOwner) { countryItems ->
            countryItems.firstOrNull()?.let { country ->
                populateCountryDetails(country)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupActionBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = args.countryName
    }

    private fun populateCountryDetails(country: CountryItem) {
        binding.apply {
            Glide.with(binding.root.context)
                .load(country.flags.png)
                .into(detailFlagIv)
            capitalText.text = "Capital: \n${country.capital?.get(0) ?: "No capital"}"
            coordinatesText.text = "Capital coordinates: \n${country.capitalInfo.latlng}"
            populationText.text = "Population:\n${country.population.toString()}"
            areaText.text = "Area: \n${country.area.toString()} km²"
            val tengeText = country.currencies?.entries?.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                ?.joinToString("\n")
             currencyText.text = "Currency: \n${tengeText}"
            regionText.text = "Region: \n${country.subregion}"
            timezonesText.text = "Timezones: \n${country.timezones}"
        }
    }
}