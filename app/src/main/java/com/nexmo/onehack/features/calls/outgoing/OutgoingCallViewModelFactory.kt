package com.nexmo.onehack.features.calls.outgoing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexmo.client.NexmoClient
import com.nexmo.onehack.features.calls.CallManager

class OutgoingCallViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        OutgoingCallViewModel(
            nexmoCall = CallManager.onGoingCall,
            nexmoClient = NexmoClient.get()
        ) as T
}
