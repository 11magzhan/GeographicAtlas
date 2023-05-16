package com.example.geographicatlas.ui.countriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.FragmentCountriesListBinding
import com.example.geographicatlas.ui.adapter.RecyclerViewAdapter

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
        binding.test.text = "test11"
        binding.recyclerView.adapter = adapter


        viewModel.remoteAllCountries.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

//        lifecycleScope.launch {
//            val countries = RetrofitClient.apiService.getAllCountries()
//            adapter.setData(countries)
//        }


    }
}