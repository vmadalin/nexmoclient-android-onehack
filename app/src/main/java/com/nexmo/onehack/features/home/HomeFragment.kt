package com.nexmo.onehack.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nexmo.client.NexmoClient
import com.nexmo.onehack.R
import com.nexmo.onehack.features.calls.CallManager

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NexmoClient.get().addIncomingCallListener {
            CallManager.onGoingCall = it
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToIncomingCallFragment())
        }
    }
}
