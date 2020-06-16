package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater

class SentdMessageViewHolder(
    inflater: LayoutInflater
) : EventViewHolder(inflater) {

    fun bind(content: String?, time:String?) {
        binding.textContent = content
        binding.time = time
        binding.executePendingBindings()
    }
}
