package com.nexmo.onehack.features.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nexmo.utils.logger.Log
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoConversation
import com.nexmo.client.NexmoConversationsPage
import com.nexmo.client.NexmoPageOrder
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener

class ChatsViewModel: ViewModel() {
    private val TAG = ChatsViewModel::class.java.name
    private val client = NexmoClient.get()

    private val _conversations = MutableLiveData<List<NexmoConversation>>()

    val conversations: LiveData<List<NexmoConversation>>
        get() = _conversations

    init {
        getConversations()
    }

    fun getConversations() {
        client.getConversations(10, "JOINED", NexmoPageOrder.NexmoMPageOrderAsc, object: NexmoRequestListener<NexmoConversationsPage> {
            override fun onError(apiError: NexmoApiError) {
                Log.d(TAG, "onError getConversations $apiError")
            }

            override fun onSuccess(result: NexmoConversationsPage?) {
                Log.d(TAG, "onSuccess getConversations $result")
                _conversations.postValue(result!!.data.toList())
            }
        })
    }
}
