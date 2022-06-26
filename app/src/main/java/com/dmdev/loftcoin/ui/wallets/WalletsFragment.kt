package com.dmdev.loftcoin.ui.wallets

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.databinding.FragmentWalletsBinding
import com.dmdev.loftcoin.di.components.BaseComponent
import com.dmdev.loftcoin.di.components.DaggerWalletsComponent
import com.dmdev.loftcoin.utils.PriceFormatter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class WalletsFragment @Inject constructor(
    baseComponent: BaseComponent,
    priceFormatter: PriceFormatter
) : Fragment() {
    private lateinit var binding: FragmentWalletsBinding
    private val walletsAdapter = WalletsAdapter()

    private val component = DaggerWalletsComponent.builder().baseComponent(baseComponent).build()
    private val viewModel by viewModels<WalletsViewModel> { component.viewModelFactory() }
    private val walletsSnapHelper = PagerSnapHelper()
    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWalletsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter(view)
        initObservers()
        initListeners()
    }

    private fun initListeners() {
    }

    private fun initObservers() {
        disposable.addAll(
            viewModel.getWallets().subscribe { walletsAdapter.submitList(it) },
            viewModel.getWallets().map { it.isEmpty() }.subscribe {
                binding.groupAddWallet.visibility = if (it) View.VISIBLE else View.GONE
                binding.rvWallets.visibility = if (!it) View.VISIBLE else View.GONE
                binding.rvTransactions.visibility = if (!it) View.VISIBLE else View.GONE
            }
//            viewModel.isLoading().subscribe {
//                binding.swipeRefresh.isRefreshing = it
//                binding.recyclerView.visibility = if (it) View.GONE else View.VISIBLE
//            }
        )
    }

    private fun initAdapter(view: View) {
        val value = TypedValue()
        view.context.theme.resolveAttribute(R.attr.walletCardWidth, value, true)
        val displayMetrics = view.context.resources.displayMetrics
        val padding = (displayMetrics.widthPixels - value.getDimension(displayMetrics)).toInt() / 2
        with(binding.rvWallets) {
            setPadding(padding, 0, padding, 0)
            clipToPadding = false

            walletsSnapHelper.attachToRecyclerView(this)
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
//            addOnScrollListener(CarouselScroller())
            swapAdapter(walletsAdapter, false)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        walletsSnapHelper.attachToRecyclerView(null)
        binding.rvWallets.swapAdapter(null, false)
        disposable.clear()
        super.onDestroyView()
    }
}