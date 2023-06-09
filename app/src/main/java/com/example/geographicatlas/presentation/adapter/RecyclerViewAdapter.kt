package com.example.geographicatlas.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.R
import com.example.geographicatlas.databinding.ItemHeaderBinding
import com.example.geographicatlas.databinding.ItemViewBinding
import com.example.geographicatlas.presentation.countries_list.CountriesListFragmentDirections
import com.example.geographicatlas.presentation.model.CountryModel
import com.example.geographicatlas.presentation.model.Item

class RecyclerViewAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(Diff()) {

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is Item.Country -> COUNTRY
            is Item.Header -> HEADER
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER -> {
                val binding =
                    ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            COUNTRY -> {
                val binding =
                    ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                CountryViewHolder(binding)
            }
            else -> throw java.lang.IllegalArgumentException("Invalid type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)

        when (holder) {
            is CountryViewHolder -> {
                //Log.d("adapter", "test2${}")
                val rowItem = currentItem as? Item.Country
                rowItem?.let {
                    Log.d("adapter", "test3")
                    holder.bind(it.country)
                }
            }
            is HeaderViewHolder -> {
                val headerWrapper = currentItem as? Item.Header
                headerWrapper?.let {
                    holder.bind(it.continents)
                }
            }
        }
    }

    inner class CountryViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryModel) {
            // Log.d("adapter", "test1${binding.root}")
            binding.nameTv.text = item.name.common
            binding.capitalTv.text = item.capital?.get(0) ?: "no"
            binding.populationTv.text = "Population: ${item.population.toString()}"
            binding.areaTv.text = "Area: ${item.area.toString()} km²"
            val currenciesText =
                item.currencies?.entries?.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                    ?.joinToString("\n")
            binding.currenciesTv.text = "Currencies: $currenciesText"
            Glide.with(binding.root.context)
                .load(item.flags.png)
                .into(binding.flagIv)
            binding.dropDown.setImageResource(
                if (item.isExpanded) R.drawable.ic_arrow_drop_up
                else R.drawable.ic_arrow_drop_down
            )
            binding.expandableLayout.isVisible = item.isExpanded

            binding.itemCard.setOnClickListener {

                item.isExpanded = !item.isExpanded
                notifyItemChanged(bindingAdapterPosition)
            }
            binding.learnMore.setOnClickListener {
                val countryId = item.cca2
                val countryName = item.name.common
                val action =
                    CountriesListFragmentDirections.openCountryDetails(countryId, countryName)
                Navigation.findNavController(itemView).navigate(action)
            }
        }
    }

    class HeaderViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(header: String) {
            binding.header.text = header
        }
    }

    class Diff : DiffUtil.ItemCallback<Item>() {
        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }

    companion object {
        private const val HEADER = 0
        private const val COUNTRY = 1
    }
}