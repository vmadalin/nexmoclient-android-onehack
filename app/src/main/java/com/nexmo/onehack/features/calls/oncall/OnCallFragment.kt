package com.nexmo.onehack.features.calls.oncall

import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnCallBinding

class OnCallFragment : BaseFragment<FragmentOnCallBinding, OnCallViewModel>(
    layoutId = R.layout.fragment_on_call
) {

    override val viewModel by viewModels<OnCallViewModel> {
        OnCallViewModelFactory()
    }
}
