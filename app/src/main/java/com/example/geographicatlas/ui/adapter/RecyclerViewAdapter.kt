package com.example.geographicatlas.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.R
import com.example.geographicatlas.data.CountryItem
import com.example.geographicatlas.databinding.ItemHeaderBinding
import com.example.geographicatlas.databinding.ItemViewBinding
import com.example.geographicatlas.ui.model.Item

class RecyclerViewAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(Diff()) {

    private var expandedItems: ArrayList<CountryItem> = arrayListOf()
    private var itemList = listOf<Item>()

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is Item.Country -> COUNTRY
            is Item.Header -> HEADER
            else -> throw IllegalArgumentException("Invalid item type at position $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            HEADER -> {
                val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                    HeaderViewHolder(binding)
            }
            COUNTRY -> {
                val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

        fun bind(item: CountryItem) {
            Log.d("adapter", "test1${binding.root}")
            val isExpanded = expandedItems.contains(item)
            binding.nameTv.text = item.name.common
            binding.capitalTv.text = item?.capital?.get(0) ?: "no"
            binding.populationTv.text = "Population: ${item.population.toString()}"
            binding.areaTv.text = "Area: ${item.area.toString()}"
            val currenciesText = item.currencies?.entries?.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }
                ?.joinToString("\n")
            binding.currenciesTv.text = currenciesText
            Glide.with(binding.root.context)
                .load(item.flag.png)
                .into(binding.flagIv)
            binding.dropDown.setImageResource(
                if (isExpanded) R.drawable.ic_arrow_drop_up
                else R.drawable.ic_arrow_drop_down
            )
            binding.expandableLayout.isVisible = isExpanded

            binding.dropDown.setOnClickListener {
                if (expandedItems.contains(item)) {
                    expandedItems.remove(item)
                } else {
                    expandedItems.add(item)
                }
//                notifyItemChanged(bindingAdapterPosition)
            }

        }
    }
    class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root) {

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