package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nexmo.client.*
import java.text.SimpleDateFormat

internal enum class ItemView(val type: Int) {
    RECEIVED(type = 0),
    SENT(type = 1);

    companion object {
        fun valueOf(type: Int): ItemView? = values().first { it.type == type }
    }
}

class MessageListViewAdapter: ListAdapter<NexmoEvent, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<NexmoEvent>() {
    override fun areItemsTheSame(old: NexmoEvent, new: NexmoEvent): Boolean = old.id == new.id
    override fun areContentsTheSame(old: NexmoEvent, new: NexmoEvent): Boolean = old == new
}) {

    var listener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (ItemView.valueOf(viewType)) {
            ItemView.RECEIVED -> ReceivedMessageViewHolder(layoutInflater)
            else -> SentMessageViewHolder(layoutInflater)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val event = getItem(position)
        when (holder) {
            is ReceivedMessageViewHolder -> {
                holder.bind(
                    "${(event as NexmoTextEvent).fromMember.user.name}:",
                    event.text,
                    simpleDateFormat.format(event.creationDate)
                )
            }
            is SentMessageViewHolder -> {
                holder.bind(
                    (event as NexmoTextEvent).text,
                    simpleDateFormat.format(event.creationDate)
                )
            }
        }
    }

    override fun getItemViewType(position: Int) = getItemView(position).type

    private fun getItemView(position: Int): ItemView {
        val event = getItem(position)
        val isMe = event.fromMember.user.name == NexmoClient.get().user.name
        return if (isMe) {
            ItemView.SENT
        } else {
            ItemView.RECEIVED
        }
    }

    fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup =
        object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 1
        }
    }
}
