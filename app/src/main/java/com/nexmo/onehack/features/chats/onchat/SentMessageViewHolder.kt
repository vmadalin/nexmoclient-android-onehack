package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import com.nexmo.onehack.base.BaseViewHolder
import com.nexmo.onehack.databinding.ItemSentMessageBinding

class SentMessageViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ItemSentMessageBinding>(
    binding = ItemSentMessageBinding.inflate(inflater)
)  {

    fun bind(content: String?, time:String?) {
        binding.textContent = content
        binding.time = time
        binding.executePendingBindings()
    }
}
