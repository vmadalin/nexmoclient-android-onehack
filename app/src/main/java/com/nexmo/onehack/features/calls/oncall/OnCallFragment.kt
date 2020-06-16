package com.nexmo.onehack.features.calls.oncall

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentOnCallBinding
import com.nexmo.onehack.features.calls.oncall.OnCallViewStates.*
import kotlinx.android.synthetic.main.fragment_on_call.*

class OnCallFragment : BaseFragment<FragmentOnCallBinding, OnCallViewModel>(
    layoutId = R.layout.fragment_on_call
) {

    private val args by navArgs<OnCallFragmentArgs>()
    override val viewModel by viewModels<OnCallViewModel> {
        OnCallViewModelFactory()
    }

    override fun onDetach() {
        viewModel.hangup()
        super.onDetach()
    }

    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onStateChange)
        viewModel.startCall(args.userName)
        username.text = args.userName
        viewBinding.viewModel = viewModel
    }

    private fun onStateChange(state: OnCallViewStates) {
        when (state) {
            CALL_RINGING -> {
                status.visibility = View.VISIBLE
                chronometer.visibility = View.GONE
            }
            CALL_ANSWERED -> {
                status.visibility = View.GONE
                chronometer.visibility = View.VISIBLE
                chronometer.start()
            }
            CALL_ERROR -> Toast.makeText(
                requireContext(),
                R.string.feature_calls_on_call_answer_call_error,
                Toast.LENGTH_SHORT
            ).show()
            CALL_REJECTED, CALL_ENDED -> findNavController().popBackStack()
        }
    }
}
