package com.nexmo.onehack.features.chats.onchat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.*
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.utils.logger.Log
import com.nexmo.onehack.features.chats.ChatManager

class OnChatViewModel: ViewModel() {

    val TAG = OnChatViewModel::class.java.name

    private val conversation: NexmoConversation? = ChatManager.nexmoConversation
    private var eventList = mutableListOf<NexmoEvent>()

    private val _event = MutableLiveData<MutableList<NexmoEvent>>()
    val event: LiveData<MutableList<NexmoEvent>>
        get() = _event

    init {
        addListeners()
        conversation?.getEvents(10, NexmoPageOrder.NexmoPageOrderDesc, null, EventsPageListener())
    }

    fun addListeners() {
        conversation?.addMessageEventListener(object : NexmoMessageEventListener{
            override fun onTypingEvent(p0: NexmoTypingEvent) {}

            override fun onAttachmentEvent(p0: NexmoAttachmentEvent) {}

            override fun onTextEvent(textEvent: NexmoTextEvent) {
                if (!eventList.contains(textEvent)) {
                    val list = emptyList<NexmoEvent>().toMutableList()
                    list.add(textEvent)
                    list.addAll(eventList)
                    eventList = list
                    _event.postValue(eventList)
                }
            }

            override fun onSeenReceipt(p0: NexmoSeenEvent) {}

            override fun onEventDeleted(p0: NexmoDeletedEvent) {}

            override fun onDeliveredReceipt(p0: NexmoDeliveredEvent) {}
        })
    }

    fun sendMessage(text: String) {
        if (text.isBlank()) {
            return
        }

        Log.d(TAG, "Send message: $text")

        conversation?.sendText(text, object: NexmoRequestListener<Void> {
            override fun onSuccess(result: Void?) {
                Log.d(TAG, "onError sendMessage $result")
            }

            override fun onError(apiError: NexmoApiError) {
                Log.d(TAG, "onError sendMessage $apiError")
            }
        })
    }

    inner class EventsPageListener : NexmoRequestListener<NexmoEventsPage>{

        override fun onError(error: NexmoApiError) {}

        override fun onSuccess(result: NexmoEventsPage?) {
            for (event: NexmoEvent in result!!.data){
                when(event){
                    is NexmoTextEvent -> {
                        event.markAsDelivered(object: NexmoRequestListener<Any>{
                            override fun onSuccess(result: Any?) {
                                event.updateAsDelivered(event.conversation.myMember.memberId, (System.currentTimeMillis()/1000).toString())
                            }

                            override fun onError(error: NexmoApiError) {}
                        })
                        if (!eventList.contains(event)) {
                            eventList.add(event)
                        }
                    }
                }
            }
            if (result.isNextPageExist){
                result.getNext(EventsPageListener())
            } else {
                _event.postValue(eventList)
            }
        }
    }
}
