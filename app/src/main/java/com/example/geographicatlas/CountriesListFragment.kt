package com.example.geographicatlas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.geographicatlas.databinding.FragmentCountriesListBinding

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private lateinit var binding: FragmentCountriesListBinding
    private val viewModel: CountriesListViewModel by viewModels()
    private val adapter: RecyclerViewAdapter by lazy { RecyclerViewAdapter(viewModel.countries) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCountriesListBinding.bind(view)
        binding.recyclerView.adapter = adapter
    }
}