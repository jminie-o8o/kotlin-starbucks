package com.example.kotlin_starbucks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentHomeBinding
import com.example.kotlin_starbucks.databinding.FragmentWhatsNewBinding
import com.example.kotlin_starbucks.ui.listAdapter.WhatNewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WhatsNewFragment : Fragment() {

    private lateinit var binding: FragmentWhatsNewBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_whats_new, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.rvWhatNew.adapter = WhatNewAdapter().apply {
            viewModel.homeEvents.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }
        return binding.root
    }
}