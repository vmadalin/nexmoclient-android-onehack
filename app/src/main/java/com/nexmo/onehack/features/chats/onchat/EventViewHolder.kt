package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import com.nexmo.onehack.base.BaseViewHolder
import com.nexmo.onehack.databinding.ItemReceivedMessageBinding

abstract  class EventViewHolder(
    inflater: LayoutInflater
): BaseViewHolder<ItemReceivedMessageBinding>(
    binding = ItemReceivedMessageBinding.inflate(inflater)
) {

    fun bind() {}
}
