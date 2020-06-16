package com.nexmo.onehack.features.chats

import android.view.LayoutInflater
import com.nexmo.client.NexmoConversation
import com.nexmo.onehack.base.BaseViewHolder
import com.nexmo.onehack.databinding.ItemConversationBinding

class ConversationViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ItemConversationBinding>(
    binding = ItemConversationBinding.inflate(inflater)
) {
    fun bind(conversation: NexmoConversation) {
        binding.conversation = conversation
        binding.executePendingBindings()
    }
}
