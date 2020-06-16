package com.nexmo.onehack.features.calls.oncall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nexmo.client.NexmoClient
import com.nexmo.onehack.features.calls.CallManager

class OnCallViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        OnCallViewModel(
            nexmoClient = NexmoClient.get()
        ) as T
}
