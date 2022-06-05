package com.dmdev.loftcoin.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmdev.loftcoin.databinding.FragmentConverterBinding

class ConverterFragment : Fragment() {
    private lateinit var binding: FragmentConverterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentConverterBinding.inflate(layoutInflater)
        return binding.root
    }
}