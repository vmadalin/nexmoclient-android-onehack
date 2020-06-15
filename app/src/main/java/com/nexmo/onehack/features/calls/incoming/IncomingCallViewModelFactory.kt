package com.nexmo.onehack.features.calls.incoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexmo.client.NexmoClient
import com.nexmo.onehack.features.calls.CallManager

class IncomingCallViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        IncomingCallViewModel(
            nexmoCall = CallManager.onGoingCall,
            nexmoClient = NexmoClient.get()
        ) as T
}
