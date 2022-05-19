package com.example.kotlin_starbucks.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentHomeBinding
import com.example.kotlin_starbucks.ui.common.ProgressDialog
import com.example.kotlin_starbucks.ui.common.UiState
import com.example.kotlin_starbucks.ui.common.clicks
import com.example.kotlin_starbucks.ui.listAdapter.HomeAdapter
import com.example.kotlin_starbucks.ui.listAdapter.HomeEventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by activityViewModels()
    private var btnClicked = 0

    @OptIn(FlowPreview::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    @OptIn(FlowPreview::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = HomeAdapter()
        val dialog = ProgressDialog(requireContext())
        viewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                is UiState.Loading ->
                    dialog.show()
                is UiState.Success -> {
                    dialog.dismiss()
                    viewModel.yourRecommendProducts.observe(viewLifecycleOwner) {
                        adapter.submitList(it)
                    }
                }
            }
        }

        viewModel.error.observe(viewLifecycleOwner) {
            Log.d("에러", it.errorMessage)
            Toast.makeText(requireContext(), "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.tvWhatNew.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_whatsNewFragment)
        }

        binding.rvYourRecommend.adapter = adapter

        binding.rvHomeEvents.adapter = HomeEventsAdapter().apply {
            viewModel.homeEvents.observe(viewLifecycleOwner) {
                submitList(it)
            }
        }

        binding.btnCheckDebounce.clicks().sample(2000).onEach {
            if(btnClicked % 2 == 0) {
                this.view?.isSelected = false
                btnClicked++
                return@onEach
            }
            btnClicked++
            this.view?.isSelected = true
        }.launchIn(lifecycleScope)
    }
}