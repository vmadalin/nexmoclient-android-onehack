package com.nexmo.onehack.features.chats.onchat

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.client.NexmoEvent
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnChatBinding
import com.nexmo.onehack.features.chats.ChatManager
import kotlinx.android.synthetic.main.fragment_on_chat.*

class OnChatFragment : BaseFragment<FragmentOnChatBinding, OnChatViewModel>(
    layoutId = R.layout.fragment_on_chat,
    titleId = R.string.feature_on_chat_title
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

        val linearLayoutManager = GridLayoutManager(requireContext(), 1)
        linearLayoutManager.spanSizeLookup= onChatRecyclerViewAdapter.getSpanSizeLookup()
        linearLayoutManager.reverseLayout = true
        message_list.layoutManager = linearLayoutManager
        message_list.adapter = onChatRecyclerViewAdapter
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.call_button -> {
                val allMembers = ChatManager.nexmoConversation?.allMembers!!
                if (allMembers.size > 1) {
                    val userName = allMembers.last()?.user?.name
                    userName?.let {
                        OnChatFragmentDirections.actionOnChatFragmentToOnCallFragment(it)
                    }
                }
                true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun onEventReceipt(eventsList: List<NexmoEvent>) {
        onChatRecyclerViewAdapter.submitList(eventsList)
        message_list.smoothScrollToPosition(0)
    }
}
