package com.dmdev.loftcoin.ui.wallets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.data.models.Wallet
import com.dmdev.loftcoin.databinding.LayoutItemWalletBinding
import com.dmdev.loftcoin.utils.PriceFormatter
import com.squareup.picasso.Picasso

class WalletsAdapter(
    private val priceFormatter: PriceFormatter
) : ListAdapter<Wallet, WalletsAdapter.ViewHolder>(DiffUtilCallback) {
    object DiffUtilCallback : DiffUtil.ItemCallback<Wallet>() {
        override fun areItemsTheSame(oldItem: Wallet, newItem: Wallet): Boolean =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: Wallet, newItem: Wallet): Boolean = oldItem == newItem
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    class ViewHolder(val binding: LayoutItemWalletBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutItemWalletBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            textName.text = item.coin.name
            textRate.text = priceFormatter.format(item.coin.price)
            textBalance.text = priceFormatter.format(item.balance, item.coin.symbol)
            Picasso.get()
                .load(BuildConfig.IMG_ENDPOINT.replace("{id}", item.coin.id.toString()))
                .into(image)
        }
    }
}