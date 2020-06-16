package com.nexmo.onehack.features.calls.outgoing

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOutgoingCallBinding
import com.nexmo.onehack.features.calls.outgoing.OutgoingCallViewStates.CALL_IN_PROGRESS
import com.nexmo.onehack.features.calls.outgoing.OutgoingCallViewStates.CALL_ERROR
import com.nexmo.onehack.features.calls.outgoing.OutgoingCallViewStates.CALL_REJECTED
import com.nexmo.onehack.features.calls.outgoing.OutgoingCallViewStates.CALL_ENDED

class OutgoingCallFragment : BaseFragment<FragmentOutgoingCallBinding, OutgoingCallViewModel>(
    layoutId = R.layout.fragment_outgoing_call
) {

    override val viewModel by viewModels<OutgoingCallViewModel> {
        OutgoingCallViewModelFactory()
    }

    override fun onDetach() {
        viewModel.endCall()
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onStateChange)
        viewBinding.viewModel = viewModel
    }

    private fun onStateChange(state: OutgoingCallViewStates) {
        when (state) {
            CALL_IN_PROGRESS ->
                findNavController()
                    .navigate(OutgoingCallFragmentDirections.actionOutgoingCallFragmentToOnCallFragment())
            CALL_ERROR -> Toast.makeText(
                requireContext(),
                R.string.feature_calls_outgoing_answer_call_error,
                Toast.LENGTH_SHORT
            ).show()
            CALL_REJECTED -> findNavController().popBackStack()
            CALL_ENDED -> findNavController().popBackStack()
        }
    }
}
