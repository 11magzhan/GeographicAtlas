package com.example.geographicatlas.ui.countryDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
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
        viewModel.onViewInit(args.countryId)

        viewModel.remoteCountry.observe(viewLifecycleOwner) {
            binding.apply {val country = it[0]
                Glide.with(binding.root.context)
                    .load(country.flag.png)
                    .into(binding.detailFlagIv)
                capitalText.text = "${country.capital?.get(0) ?: "No capital"}"
                populationText.text = country.population.toString()
                areaText.text =  country.area.toString()
                val tengeText = country.currencies?.entries?.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                    ?.joinToString("\n")
                currencyText.text = tengeText
                regionText.text = country.subregion
            }
        }

        //binding = FragmentCountryDetailsBinding.bind(view)
    }

}