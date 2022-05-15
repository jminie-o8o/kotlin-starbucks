package com.example.kotlin_starbucks.ui.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_starbucks.databinding.ItemHomeEventsBinding
import com.example.kotlin_starbucks.databinding.ItemWhatNewBinding
import com.example.kotlin_starbucks.model.HomeEvents

class WhatNewAdapter :
    ListAdapter<HomeEvents.HomeEventsContents, WhatNewAdapter.WhatNewEventsHolder>(
        HomeEventsDiffCallBack
    ) {

    private lateinit var binding: ItemWhatNewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WhatNewEventsHolder {
        binding = ItemWhatNewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WhatNewEventsHolder(binding)
    }

    override fun onBindViewHolder(holder: WhatNewEventsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WhatNewEventsHolder(private val binding: ItemWhatNewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(events: HomeEvents.HomeEventsContents) {
            binding.events = events
            binding.executePendingBindings()
        }
    }

    object HomeEventsDiffCallBack: DiffUtil.ItemCallback<HomeEvents.HomeEventsContents>() {
        override fun areItemsTheSame(
            oldItem: HomeEvents.HomeEventsContents,
            newItem: HomeEvents.HomeEventsContents
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: HomeEvents.HomeEventsContents,
            newItem: HomeEvents.HomeEventsContents
        ): Boolean {
            return oldItem == newItem
        }
    }
}