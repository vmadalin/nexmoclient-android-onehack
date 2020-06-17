package com.nexmo.onehack.features.calls.incoming

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentIncomingCallBinding
import com.nexmo.onehack.features.calls.incoming.IncomingCallViewStates.CALL_ACCEPTED
import com.nexmo.onehack.features.calls.incoming.IncomingCallViewStates.CALL_ACCEPT_ERROR
import com.nexmo.onehack.features.calls.incoming.IncomingCallViewStates.CALL_REJECTED

class IncomingCallFragment : BaseFragment<FragmentIncomingCallBinding, IncomingCallViewModel>(
    layoutId = R.layout.fragment_incoming_call,
    titleId = R.string.feature_calls_incoming_title
) {

    override val viewModel by viewModels<IncomingCallViewModel> {
        IncomingCallViewModelFactory()
    }

    override fun onDetach() {
        viewModel.rejectCall()
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onStateChange)
        viewBinding.viewModel = viewModel
    }

    private fun onStateChange(state: IncomingCallViewStates) {
        when (state) {
            CALL_ACCEPTED -> {
                findNavController().navigate(
                    IncomingCallFragmentDirections.actionIncomingCallFragmentToOnCallFragment(
                        viewModel.callerName
                    )
                )
            }
            CALL_ACCEPT_ERROR -> Toast.makeText(
                requireContext(),
                R.string.feature_calls_incoming_answer_call_error,
                Toast.LENGTH_SHORT
            ).show()
            CALL_REJECTED -> findNavController().popBackStack()
        }
    }
}
