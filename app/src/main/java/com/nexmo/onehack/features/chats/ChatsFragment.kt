package com.nexmo.onehack.features.chats

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.client.NexmoClient
import com.nexmo.client.NexmoConversation
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentChatsBinding
import com.nexmo.utils.logger.Log
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>(
    layoutId = R.layout.fragment_chats
) {

    val adapter = ConversationsRecyclerViewAdapter()

    override val viewModel by viewModels<ChatsViewModel> {
        ChatsViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.conversations, ::onConversationListChange)
        adapter.listener = View.OnClickListener {
            ChatManager.nexmoConversation = it.tag as NexmoConversation
            findNavController().navigate(ChatsFragmentDirections.actionChatsFragmentToOnChatFragment())
        }
        conversations_recycler.adapter = adapter
    }

    fun onConversationListChange(conversations: List<NexmoConversation>) {
        adapter.submitList(conversations)
    }
}
