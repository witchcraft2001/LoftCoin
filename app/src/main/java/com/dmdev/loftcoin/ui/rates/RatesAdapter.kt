package com.dmdev.loftcoin.ui.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.databinding.LayoutItemRatesBinding

class RatesAdapter : ListAdapter<Coin, RatesAdapter.ViewHolder>(DiffUtilCallback) {
    object DiffUtilCallback : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean =
            oldItem.equals(newItem)
    }

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    class ViewHolder(val binding: LayoutItemRatesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutItemRatesBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            textName.text = item.name
            textPrice.text = item.price.toString()
            textChange.text = item.change24h.toString()
            textChange.setTextColor(
                holder.itemView.context.resources.getColor(
                    if (item.change24h >= 0) {
                        R.color.weird_green
                    } else {
                        R.color.watermelon
                    }
                )
            )
        }
    }
}