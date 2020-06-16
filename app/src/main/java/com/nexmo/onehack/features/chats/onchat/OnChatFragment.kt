package com.nexmo.onehack.features.chats.onchat

import android.os.Bundle
import android.view.View
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

class OnChatFragment : BaseFragment<FragmentOnChatBinding, ChatsViewModel>(
    layoutId = R.layout.fragment_on_chat
) {

    override val viewModel by viewModels<ChatsViewModel> {
        OnChatViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //observe(viewModel.conversation, ::onConversationChange)
    }

    fun onConversationChange(conversation: NexmoConversation) {


    }
}
