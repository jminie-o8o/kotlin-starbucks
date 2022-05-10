package com.example.kotlin_starbucks.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.kotlin_starbucks.R
import com.example.kotlin_starbucks.databinding.FragmentMainEventBinding
import com.example.kotlin_starbucks.model.EventImage

class MainEventFragment : Fragment() {

    private lateinit var binding: FragmentMainEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_event, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            eventImage = EventImage()
            btnTemp.setOnClickListener {
                findNavController().navigate(R.id.action_mainEventFragment_to_homeFragment)
            }
        }
    }
}