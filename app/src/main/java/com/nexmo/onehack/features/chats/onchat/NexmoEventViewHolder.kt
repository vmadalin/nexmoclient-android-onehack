package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import com.nexmo.onehack.base.BaseViewHolder
import com.nexmo.onehack.databinding.ItemReceivedMessageBinding

class NexmoEventViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ItemReceivedMessageBinding>(
    binding = ItemReceivedMessageBinding.inflate(inflater)
)  {

    fun bind(senderName: String?, content: String?, time:String?) {
        binding.textContent = content
        binding.time = time
        binding.senderName = senderName
        binding.executePendingBindings()
    }
}
