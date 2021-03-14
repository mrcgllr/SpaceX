package com.android.spacex.ui.list.dialog.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.spacex.databinding.ItemYearBinding

class YearsViewHolder(private val binding: ItemYearBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: String, itemClickCallback: (String) -> Unit?) {
        binding.model = item
        binding.itemContainer.setOnClickListener {
            itemClickCallback.invoke(item)
        }
    }
}