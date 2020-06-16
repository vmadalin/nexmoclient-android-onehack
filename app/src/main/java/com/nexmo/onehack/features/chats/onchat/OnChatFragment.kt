package com.nexmo.onehack.features.chats.onchat

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.client.NexmoConversation
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnChatBinding
import com.nexmo.onehack.features.chats.ChatManager
import com.nexmo.onehack.features.chats.ChatsViewModel
import com.nexmo.onehack.features.chats.ChatsViewModelFactory
import com.nexmo.onehack.features.chats.ConversationsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_chats.*
import kotlinx.android.synthetic.main.fragment_on_chat.*

class OnChatFragment : BaseFragment<FragmentOnChatBinding, OnChatViewModel>(
    layoutId = R.layout.fragment_on_chat
) {

    override val viewModel by viewModels<OnChatViewModel> {
        OnChatViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observe(viewModel.conversation, ::onConversationChange)
        button_chatbox_send.setOnClickListener {
            val textMessage = edittext_chatbox.text.toString()
            viewModel.sendMessage(textMessage)
        }
    }

    fun onConversationChange(conversation: NexmoConversation) {


    }
}
