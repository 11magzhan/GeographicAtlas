package com.example.geographicatlas.presentation.countries_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.presentation.adapter.RecyclerViewAdapter
import com.example.geographicatlas.utilities.Resource

class CountriesListFragment : Fragment(R.layout.fragment_countries_list) {

    private var _binding: FragmentCountriesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CountriesListViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountriesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RecyclerViewAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.remoteAllCountries.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.shimmerLayout.startShimmer()
                }
                is Resource.Success -> {
                    it.data.let {
                        showRecyclerView()
                        adapter.submitList(it)
                    }
                }
                else -> {
                    Log.d("State", "Error state")
                }
            }
        }
    }

    private fun showRecyclerView() {
        binding.shimmerLayout.apply {
            stopShimmer()
            visibility = View.GONE
        }
        binding.recyclerView.visibility = View.VISIBLE
    }
}