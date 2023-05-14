package com.example.geographicatlas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.geographicatlas.databinding.ItemViewBinding

class RecyclerViewAdapter(
    private val itemList: List<CountryItem>,
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var expandedItems: ArrayList<CountryItem> = arrayListOf()

    inner class ViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CountryItem) {
            val isExpanded = expandedItems.contains(item)
            binding.nameTv.text = item.name
            binding.capitalTv.text = item.capital
            binding.populationTv.text = item.population
            binding.areaTv.text = item.area.toString()
            //binding.flagIv.setImageDrawable(R.drawable.kz)
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