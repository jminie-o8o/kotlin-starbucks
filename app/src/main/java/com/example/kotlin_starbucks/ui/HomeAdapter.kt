package com.example.kotlin_starbucks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_starbucks.databinding.ItemYourRecommendBinding
import com.example.kotlin_starbucks.model.YourRecommendProducts

class HomeAdapter : ListAdapter<YourRecommendProducts, HomeAdapter.HomeHolder>(FileDiffCallBack) {

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
        fun bind(products: YourRecommendProducts) {
            binding.product = products
            binding.executePendingBindings()
        }
    }

    object FileDiffCallBack : DiffUtil.ItemCallback<YourRecommendProducts>() {
        override fun areItemsTheSame(
            oldItem: YourRecommendProducts,
            newItem: YourRecommendProducts
        ): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(
            oldItem: YourRecommendProducts,
            newItem: YourRecommendProducts
        ): Boolean {
            return oldItem == newItem
        }
    }
}