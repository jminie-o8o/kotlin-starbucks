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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentHomeBinding
import com.example.kotlin_starbucks.ui.common.ProgressDialog
import com.example.kotlin_starbucks.ui.common.UiState
import com.example.kotlin_starbucks.ui.common.clicks
import com.example.kotlin_starbucks.ui.common.throttleFirst
import com.example.kotlin_starbucks.ui.listAdapter.HomeAdapter
import com.example.kotlin_starbucks.ui.listAdapter.HomeEventsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ViewModel by activityViewModels()

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

        viewModel.observeUiState(viewModel.uiState, viewLifecycleOwner)
        viewModel.observeUiState(viewModel.loadHomeContentsUiState, viewLifecycleOwner)
        viewModel.observeUiState(viewModel.loadHomeEventsUiState, viewLifecycleOwner)

        viewModel.error.observe(viewLifecycleOwner) {
            Log.d("에러", it.errorMessage)
            Toast.makeText(requireContext(), "오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
        }

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

        binding.btnCheckDebounce.clicks().throttleFirst(3000).onEach {
            this.view?.isSelected = this.view?.isSelected != true
        }.launchIn(lifecycleScope)
    }


    private fun <T> ViewModel.observeUiState(liveData: LiveData<T>, lifecycleOwner: LifecycleOwner) {
        val dialog = ProgressDialog(requireContext())
        liveData.observe(lifecycleOwner) {
            when (it) {
                is UiState.Loading ->
                    dialog.show()
                is UiState.Success -> {
                    dialog.dismiss()
                }
            }
        }
    }
}