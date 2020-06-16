package com.nexmo.onehack.features.calls.oncall


import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoCallHandler
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.features.calls.CallManager

class OnCallViewModel(
    private val nexmoClient: NexmoClient
) : ViewModel() {

    private val _state = MutableLiveData<OnCallViewStates>()
    val state: LiveData<OnCallViewStates>
        get() = _state

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun startCall(userName: String) {
        _state.postValue(OnCallViewStates.CALL_RINGING)
        nexmoClient.call(userName, NexmoCallHandler.IN_APP, answerCallListener)
    }

    fun hangup() {
        CallManager.onGoingCall?.hangup(object : NexmoRequestListener<NexmoCall> {
            override fun onSuccess(p0: NexmoCall?) {
                CallManager.onGoingCall = null
                _state.postValue(OnCallViewStates.CALL_ENDED)
            }

            override fun onError(p0: NexmoApiError) {
                CallManager.onGoingCall = null
                _state.postValue(OnCallViewStates.CALL_ENDED)
            }
        })
    }

    fun mute() {
        CallManager.onGoingCall?.mute(true)
    }

    private val answerCallListener = object : NexmoRequestListener<NexmoCall> {
        override fun onSuccess(call: NexmoCall?) {
            CallManager.onGoingCall = call
            _state.postValue(OnCallViewStates.CALL_ANSWERED)
        }

        override fun onError(apiError: NexmoApiError) {
            _state.postValue(OnCallViewStates.CALL_ERROR)
        }
    }
}
