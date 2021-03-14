package com.android.spacex.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.android.spacex.databinding.ItemLaunchBinding
import com.android.spacex.model.LaunchUiModel

class LaunchListViewHolder(private val binding: ItemLaunchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: LaunchUiModel, itemClickCallback: (LaunchUiModel) -> Unit?) {
        binding.model = item
        binding.btnLaunchDetail.setOnClickListener {
            itemClickCallback.invoke(item)
        }
    }
}