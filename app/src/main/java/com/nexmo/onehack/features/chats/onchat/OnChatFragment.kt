package com.nexmo.onehack.features.chats.onchat

import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnChatBinding

class OnChatFragment : BaseFragment<FragmentOnChatBinding, OnChatViewModel>(
    layoutId = R.layout.fragment_on_chat
) {

    override val viewModel by viewModels<OnChatViewModel> {
        OnChatViewModelFactory()
    }

    // TODO
}
