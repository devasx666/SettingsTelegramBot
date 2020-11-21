package com.rebus.settingstelegrambot.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rebus.settingstelegrambot.data.db.roommodels.BotsData
import com.rebus.settingstelegrambot.databinding.BotCardBinding
import com.rebus.settingstelegrambot.ui.event.OnClickListener

class BotsAdapter(
    private val bots: MutableList<BotsData>,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<BotsAdapter.BotViewHolder>() {

    override fun getItemCount(): Int =
        bots.size

    override fun onBindViewHolder(holder: BotViewHolder, position: Int) =
        holder.bind(bots.get(position), onClickListener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = BotCardBinding.inflate(inflater)

        return BotViewHolder(binding)
    }

    class BotViewHolder(private val binding: BotCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BotsData, listener: OnClickListener) {
            binding.bots = item
            binding.itemClick = listener
            binding.executePendingBindings()
        }
    }
}