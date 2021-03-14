package com.android.spacex.ui.list.dialog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.android.spacex.R
import com.android.spacex.databinding.ItemYearBinding

class YearsAdapter(private val itemClickCallback: ((String) -> Unit?)) :
    ListAdapter<String, YearsViewHolder>(object :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearsViewHolder {
        val viewRoot =
            LayoutInflater.from(parent.context).inflate(R.layout.item_year, parent, false)
        return YearsViewHolder(ItemYearBinding.bind(viewRoot))
    }

    override fun onBindViewHolder(holder: YearsViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickCallback)
    }


}