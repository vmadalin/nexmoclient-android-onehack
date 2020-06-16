package com.nexmo.onehack.features.chats.onchat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.*
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.databinding.FragmentOnCallBinding
import com.nexmo.utils.logger.Log
import com.nexmo.onehack.features.chats.ChatManager
import kotlinx.android.synthetic.main.fragment_on_chat.*

class OnChatViewModel: ViewModel() {

    val TAG = OnChatViewModel::class.java.name

    lateinit var binding: FragmentOnCallBinding
    private val client = NexmoClient.get()

    val conversation: NexmoConversation? = ChatManager.nexmoConversation

    private val _event = MutableLiveData<NexmoEvent>()

    val event: LiveData<NexmoEvent>
        get() = _event

    init {
        addListeners()
    }

    fun addListeners() {
        conversation?.addMessageEventListener(object : NexmoMessageEventListener{
            override fun onTypingEvent(p0: NexmoTypingEvent) {
                print("HELLOO")
            }

            override fun onAttachmentEvent(p0: NexmoAttachmentEvent) {
                print("HELLOO")
            }

            override fun onTextEvent(textEvent: NexmoTextEvent) {
                print("HELLOO")
//                onChatRecyclerViewAdapter.addEvent(textEvent)
                _event.postValue(textEvent)
            }

            override fun onSeenReceipt(p0: NexmoSeenEvent) {
                print("HELLOO")
            }

            override fun onEventDeleted(p0: NexmoDeletedEvent) {
                print("HELLOO")
            }


            override fun onDeliveredReceipt(p0: NexmoDeliveredEvent) {
//                adapter.addEvent(p0)
//                binding.recyclerView.scrollToPosition(adapter.itemCount - 1)
//                binding.reyclerviewMessageList.scrollToPosition(adapter.itemCount - 1)
                print("HELLOO")
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
