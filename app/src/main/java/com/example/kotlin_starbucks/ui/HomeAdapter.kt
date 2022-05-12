package com.example.kotlin_starbucks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_starbucks.databinding.ItemYourRecommendBinding
import com.example.kotlin_starbucks.model.File

class HomeAdapter : ListAdapter<File, HomeAdapter.HomeHolder>(FileDiffCallBack) {

    private lateinit var binding: ItemYourRecommendBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        binding = ItemYourRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }


    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class HomeHolder(private val binding: ItemYourRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(file: File) {
            binding.product = file
            binding.executePendingBindings()
        }
    }

    object FileDiffCallBack : DiffUtil.ItemCallback<File>() {
        override fun areItemsTheSame(
            oldItem: File,
            newItem: File
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: File,
            newItem: File
        ): Boolean {
            return oldItem == newItem
        }
    }
}