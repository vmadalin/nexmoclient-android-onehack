package com.nexmo.onehack.features.calls.outgoing

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.features.calls.outgoing.OutgoingCallViewStates

class OutgoingCallViewModel(
    private val nexmoClient: NexmoClient,
    private val nexmoCall: NexmoCall?
) : ViewModel() {

    val calleeName = nexmoCall?.callMembers?.first()?.user?.name ?: run { "UNKNOWN" }

    private val _state = MutableLiveData<OutgoingCallViewStates>()
    val state: LiveData<OutgoingCallViewStates>
        get() = _state

    fun startCall() {
        _state.postValue(OutgoingCallViewStates.CALL_IN_PROGRESS)
    }

    fun endCall() {
        _state.postValue(OutgoingCallViewStates.CALL_ENDED)
    }
}
