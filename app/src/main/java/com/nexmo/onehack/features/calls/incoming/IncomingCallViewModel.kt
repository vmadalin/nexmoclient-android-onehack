package com.nexmo.onehack.features.calls.incoming

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener

class IncomingCallViewModel(
    private val nexmoClient: NexmoClient,
    private val nexmoCall: NexmoCall?
) : ViewModel() {

    val callerName = nexmoCall?.callMembers?.first()?.user?.name ?: run { "UNKNOWN" }

    private val _state = MutableLiveData<IncomingCallViewStates>()
    val state: LiveData<IncomingCallViewStates>
        get() = _state

    private val answerCallListener = object : NexmoRequestListener<NexmoCall> {
        override fun onSuccess(call: NexmoCall?) {
            _state.postValue(IncomingCallViewStates.CALL_ACCEPTED)
        }

        override fun onError(apiError: NexmoApiError) {
            _state.postValue(IncomingCallViewStates.CALL_ACCEPT_ERROR)
        }
    }

    fun rejectCall() {
        _state.postValue(IncomingCallViewStates.CALL_REJECTED)
    }

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun answerCall() {
        nexmoCall?.answer(answerCallListener)
    }
}
