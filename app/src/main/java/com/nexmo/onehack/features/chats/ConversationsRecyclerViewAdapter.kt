package com.nexmo.onehack.features.chats

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexmo.client.NexmoConversation
import com.nexmo.onehack.R
import kotlinx.android.synthetic.main.item_conversation.view.*

class ConversationsRecyclerViewAdapter: ListAdapter<NexmoConversation, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<NexmoConversation>() {
    override fun areItemsTheSame(old: NexmoConversation, new: NexmoConversation): Boolean = old.conversationId == new.conversationId
    override fun areContentsTheSame(old: NexmoConversation, new: NexmoConversation): Boolean = old == new
}) {

    var listener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val conversationViewHolder = ConversationViewHolder(LayoutInflater.from(parent.context))
        return conversationViewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ConversationViewHolder -> {
                holder.itemView.setOnClickListener {
                    it.tag = getItem(position)
                    listener?.onClick(it)
                }
                holder.bind(getItem(position))
            }
        }
    }
}
