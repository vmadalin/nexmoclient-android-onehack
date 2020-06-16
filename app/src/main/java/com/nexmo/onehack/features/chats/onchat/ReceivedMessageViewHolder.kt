package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nexmo.client.NexmoConversation
import com.nexmo.onehack.base.BaseViewHolder
import com.nexmo.onehack.databinding.ItemConversationBinding
import com.nexmo.onehack.databinding.ItemReceivedMessageBinding
import com.nexmo.utils.Log
import okhttp3.OkHttpClient

class ReceivedMessageViewHolder(
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
