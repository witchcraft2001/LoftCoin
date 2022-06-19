package com.dmdev.loftcoin.ui.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.BuildConfig
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.data.models.Coin
import com.dmdev.loftcoin.databinding.LayoutItemRatesBinding
import com.dmdev.loftcoin.utils.PriceFormatter
import com.squareup.picasso.Picasso
import java.util.*

class RatesAdapter(private val priceFormatter: PriceFormatter) : ListAdapter<Coin, RatesAdapter.ViewHolder>(DiffUtilCallback) {
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
            textPrice.text = priceFormatter.format(item.price)
            textChange.text = String.format(Locale.US, "%.3f%%", item.change24h)
            textChange.setTextColor(
                holder.itemView.context.resources.getColor(
                    if (item.change24h >= 0) {
                        R.color.weird_green
                    } else {
                        R.color.watermelon
                    }
                )
            )
            Picasso.get()
                .load(BuildConfig.IMG_ENDPOINT.replace("{id}", item.id.toString()))
                .into(image)
        }
    }
}