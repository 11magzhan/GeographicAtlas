package com.example.geographicatlas.ui.countriesList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.geographicatlas.R
import com.example.geographicatlas.RecyclerViewAdapter
import com.example.geographicatlas.RetrofitClient
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import kotlinx.coroutines.launch

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private lateinit var binding: FragmentCountriesListBinding
    private val viewModel: CountriesListViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCountriesListBinding.bind(view)
        adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        lifecycleScope.launch {
            val countries = RetrofitClient.apiService.getAllCountries()
            adapter.setData(countries)
        }
    }
}