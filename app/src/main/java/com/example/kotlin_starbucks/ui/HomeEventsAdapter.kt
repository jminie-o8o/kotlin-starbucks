package com.example.kotlin_starbucks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_starbucks.databinding.ItemHomeEventsBinding
import com.example.kotlin_starbucks.model.HomeEvents

class HomeEventsAdapter :
    ListAdapter<HomeEvents.HomeEventsContents, HomeEventsAdapter.HomeEventsHolder>(HomeEventsDiffCallBack) {

    private lateinit var binding: ItemHomeEventsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeEventsHolder {
        binding = ItemHomeEventsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeEventsHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeEventsHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeEventsHolder(private val binding: ItemHomeEventsBinding) :
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