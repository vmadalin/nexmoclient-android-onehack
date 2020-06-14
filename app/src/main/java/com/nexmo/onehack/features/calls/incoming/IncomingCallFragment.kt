package com.nexmo.onehack.features.calls.incoming

import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentIncomingCallBinding

class IncomingCallFragment : BaseFragment<FragmentIncomingCallBinding, IncomingCallViewModel>(
    layoutId = R.layout.fragment_incoming_call
) {

    override val viewModel by viewModels<IncomingCallViewModel> {
        IncomingCallViewModelFactory()
    }
}
