package com.nexmo.onehack.features.chats

import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentChatsBinding

class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>(
    layoutId = R.layout.fragment_chats
) {

    override val viewModel by viewModels<ChatsViewModel> {
        ChatsViewModelFactory()
    }

    // TODO
}
