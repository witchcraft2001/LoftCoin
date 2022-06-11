package com.dmdev.loftcoin.ui.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmdev.loftcoin.databinding.FragmentRatesBinding

class RatesFragment : Fragment() {
    private lateinit var binding: FragmentRatesBinding
    private val ratesAdapter = RatesAdapter()

    private val viewModel by viewModels<RatesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRatesBinding.inflate(layoutInflater)

        initAdapter()
        initObservers()
        initListeners()

        return binding.root
    }

    private fun initObservers() {
        viewModel.listings.observe(viewLifecycleOwner) { list ->
            ratesAdapter.submitList(list)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.swipeRefresh.isRefreshing = isLoading
            binding.recyclerView.visibility = if (isLoading) GONE else VISIBLE
        }
    }

    private fun initListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    private fun initAdapter() {
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            swapAdapter(ratesAdapter, false)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        binding.recyclerView.swapAdapter(null, false)
        super.onDestroyView()
    }
}