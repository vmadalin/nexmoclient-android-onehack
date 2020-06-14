package com.nexmo.onehack.features.calls

import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentCallsBinding
import com.nexmo.onehack.features.chats.ChatsViewModelFactory

class CallsFragment : BaseFragment<FragmentCallsBinding, CallsViewModel>(
    layoutId = R.layout.fragment_calls
) {

    override val viewModel by viewModels<CallsViewModel> {
        ChatsViewModelFactory()
    }
}
