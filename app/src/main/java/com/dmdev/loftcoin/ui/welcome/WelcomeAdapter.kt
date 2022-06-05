package com.dmdev.loftcoin.ui.welcome

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dmdev.loftcoin.R
import com.dmdev.loftcoin.databinding.WelcomePageBinding

class WelcomeAdapter : RecyclerView.Adapter<WelcomeAdapter.ViewHolder>() {
    private val items = arrayOf(
        WelcomePageModel(R.string.welcome_page_title_1, R.string.welcome_page_subtitle_1, R.drawable.buy_sale_asset),
        WelcomePageModel(
            R.string.welcome_page_title_2,
            R.string.welcome_page_subtitle_2,
            R.drawable.exchange_rates_asset
        ),
        WelcomePageModel(R.string.welcome_page_title_3, R.string.welcome_page_subtitle_3, R.drawable.exchange_asset),
    )

    private lateinit var layoutInflater: LayoutInflater

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        layoutInflater = LayoutInflater.from(recyclerView.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(WelcomePageBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.title.setText(items[position].title)
        holder.binding.subtitle.setText(items[position].subtitle)
        holder.binding.image.setImageResource(items[position].image)
    }

    override fun getItemCount() = items.size

    class ViewHolder(val binding: WelcomePageBinding) : RecyclerView.ViewHolder(binding.root)
}