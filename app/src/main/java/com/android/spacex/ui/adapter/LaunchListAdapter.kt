package com.android.spacex.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.spacex.R
import com.android.spacex.databinding.ItemLaunchBinding
import com.android.spacex.model.LaunchUiModel

class LaunchListAdapter(private val itemClickCallback: ((LaunchUiModel) -> Unit?)) :
    ListAdapter<LaunchUiModel, LaunchListViewHolder>(object :
        DiffUtil.ItemCallback<LaunchUiModel>() {
        override fun areItemsTheSame(oldItem: LaunchUiModel, newItem: LaunchUiModel): Boolean {
            return oldItem.flightNumber == newItem.flightNumber
        }

        override fun areContentsTheSame(oldItem: LaunchUiModel, newItem: LaunchUiModel): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchListViewHolder {
        val viewRoot =
            LayoutInflater.from(parent.context).inflate(R.layout.item_launch, parent, false)
        return LaunchListViewHolder(ItemLaunchBinding.bind(viewRoot))
    }

    override fun onBindViewHolder(holder: LaunchListViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickCallback)
    }


}