package com.nexmo.onehack.features.chats.onchat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexmo.client.*
import com.nexmo.utils.logger.Log
import java.text.SimpleDateFormat


class MessageListViewAdapter() : RecyclerView.Adapter<ReceivedMessageViewHolder>() {

    val TAG = MessageListViewAdapter::class.java.name

    var eventsList: MutableCollection<NexmoEvent> = ArrayList<NexmoEvent>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceivedMessageViewHolder {
        val receivedMessageViewHolder = ReceivedMessageViewHolder(LayoutInflater.from(parent.context))
        return receivedMessageViewHolder
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(receivedMessageViewHolder: ReceivedMessageViewHolder, position: Int) {

        var simpleDateFormat = SimpleDateFormat("HH:mm:ss")
        val event = eventsList.elementAt(position)
        var isMe = event.fromMember.user.name == NexmoClient.get().user.name
        Log.d(TAG, "onBindViewHolder() event $event" )
        when(event.eventType){
          NexmoEventType.TEXT->
              if (isMe) {
                  receivedMessageViewHolder.bind("[${event.id}][TEXT]:${(event as NexmoTextEvent).fromMember.user.name}", event.text, simpleDateFormat.format(event.creationDate))
              } else {
//                  sentMessageViewHolder.bind("[${event.id}][TEXT]:${(event as NexmoTextEvent).fromMember.user.name}", event.text, simpleDateFormat.format(event.creationDate))
              }
        }

    }

    fun addEvent(event: NexmoEvent) {
        if (!eventsList.contains(event)) {
            eventsList.add(event)
        }
        this.notifyDataSetChanged()
    }

}
