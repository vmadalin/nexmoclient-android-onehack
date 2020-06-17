package com.nexmo.onehack.features.chats.onchat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.client.NexmoEvent
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnChatBinding
import kotlinx.android.synthetic.main.fragment_on_chat.*

class OnChatFragment : BaseFragment<FragmentOnChatBinding, OnChatViewModel>(
    layoutId = R.layout.fragment_on_chat
) {

    private val onChatRecyclerViewAdapter = MessageListViewAdapter()

    override val viewModel by viewModels<OnChatViewModel> {
        OnChatViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.event, ::onEventReceipt)

        button_chatbox_send.setOnClickListener {
            val textMessage = edittext_chatbox.text.toString()
            edittext_chatbox.text.clear()
            viewModel.sendMessage(textMessage)
        }

        val linearLayoutManager = LinearLayoutManager(requireContext())
        linearLayoutManager.stackFromEnd = true
        message_list.layoutManager = linearLayoutManager
        message_list.adapter = onChatRecyclerViewAdapter
    }

    private fun onEventReceipt(eventsList: List<NexmoEvent>) {
        onChatRecyclerViewAdapter.submitList(eventsList)
    }
}
