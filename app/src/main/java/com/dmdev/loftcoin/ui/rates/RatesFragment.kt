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
import com.dmdev.loftcoin.di.components.BaseComponent
import com.dmdev.loftcoin.di.components.DaggerRatesComponent
import com.dmdev.loftcoin.utils.PercentageFormatter
import com.dmdev.loftcoin.utils.PriceFormatter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RatesFragment @Inject constructor(
    baseComponent: BaseComponent,
    priceFormatter: PriceFormatter,
    percentageFormatter: PercentageFormatter
) : Fragment() {
    private lateinit var binding: FragmentRatesBinding
    private val ratesAdapter = RatesAdapter(priceFormatter, percentageFormatter)

    private val viewModel by viewModels<RatesViewModel>{ component.viewModelFactory() }

    private val component = DaggerRatesComponent.builder().baseComponent(baseComponent).build()

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentRatesBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        disposable.addAll(
            viewModel.getCoins().subscribe { ratesAdapter.submitList(it) },
            viewModel.isLoading().subscribe {
                binding.swipeRefresh.isRefreshing = it
                binding.recyclerView.visibility = if (it) GONE else VISIBLE }
        )
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
        disposable.clear()
        super.onDestroyView()
    }
}