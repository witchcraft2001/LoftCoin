package com.dmdev.loftcoin.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmdev.loftcoin.databinding.FragmentRatesBinding

class RatesFragment : Fragment() {
    private lateinit var binding: FragmentRatesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRatesBinding.inflate(layoutInflater)
        return binding.root
    }
}