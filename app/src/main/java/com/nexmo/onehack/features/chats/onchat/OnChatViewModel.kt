package com.nexmo.onehack.features.chats.onchat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.nexmo.base.android.extensions.observe
import com.nexmo.client.*
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.R
import com.nexmo.onehack.features.chats.ChatsViewModel
import com.nexmo.onehack.features.chats.ChatsViewModelFactory
import com.nexmo.utils.logger.Log
import kotlinx.android.synthetic.main.fragment_chats.*
import com.nexmo.onehack.features.chats.ChatManager

class OnChatViewModel: ViewModel() {

    val TAG = OnChatViewModel::class.java.name
    private val client = NexmoClient.get()

    val conversation: NexmoConversation? = ChatManager.nexmoConversation

    init {
        conversation?.addNexmoConversationListener(object : NexmoConversationListener {
            override fun conversationExpired() {
                TODO("Not yet implemented")
            }

            override fun onMemberUpdated(p0: NexmoMember, p1: NexmoMemberUpdatedType?) {
                TODO("Not yet implemented")
            }
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
}
