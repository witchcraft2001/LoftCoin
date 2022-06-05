package com.dmdev.loftcoin.ui.wallets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmdev.loftcoin.databinding.FragmentWalletsBinding

class WalletsFragment : Fragment() {
    private lateinit var binding: FragmentWalletsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWalletsBinding.inflate(layoutInflater)
        return binding.root
    }
}