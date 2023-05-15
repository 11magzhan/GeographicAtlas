package com.example.geographicatlas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.geographicatlas.data.CountryItem
import com.example.geographicatlas.databinding.ItemHeaderBinding
import com.example.geographicatlas.databinding.ItemViewBinding

class RecyclerViewAdapter(
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var expandedItems: ArrayList<CountryItem> = arrayListOf()
    private var itemList = listOf<CountryItem>()
    lateinit var cur: String
    fun setData(newList: List<CountryItem>) {
        itemList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryItem) {
            val isExpanded = expandedItems.contains(item)
            binding.nameTv.text = item.name.common
            binding.capitalTv.text = item?.capital?.get(0) ?: "no"
            binding.populationTv.text = "Population: ${item.population.toString()}"
            binding.areaTv.text = "Area: ${item.area.toString()}"
            //binding.currenciesTv.text = "Currencies: ${item.currencies['']}"
            val tengeText = item.currencies.entries.map { "${it.value.name} (${it.value.symbol}) (${it.key})" }.joinToString("\n")
            binding.currenciesTv.text = tengeText
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
                notifyItemChanged(bindingAdapterPosition)
            }

        }
    }
//    class HeaderViewHolder(private val binding: ItemHeaderBinding) : RecyclerView.ViewHolder(binding.root){
//        fun bind(header: CountryItem)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}