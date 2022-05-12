package com.example.kotlin_starbucks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by viewModels()

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
            viewModel.homeContentsDetail.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }

        return binding.root
    }
}