package com.example.kotlin_starbucks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentHomeBinding
import com.example.kotlin_starbucks.ui.listAdapter.HomeAdapter
import com.example.kotlin_starbucks.ui.listAdapter.HomeEventsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.tvWhatNew.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_whatsNewFragment)
        }

        binding.rvYourRecommend.adapter = HomeAdapter().apply {
            viewModel.yourRecommendProducts.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }

        binding.rvHomeEvents.adapter = HomeEventsAdapter().apply {
            viewModel.homeEvents.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }
        return binding.root
    }
}