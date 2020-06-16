package com.nexmo.onehack.features.calls.oncall


import android.Manifest
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoCallHandler
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.onehack.features.calls.CallManager
import com.nexmo.utils.logger.Log

class OnCallViewModel(
    private val nexmoClient: NexmoClient
) : ViewModel() {
    private val TAG = OnCallViewModel::class.java.name

    val calleeName = "UNKNOWN"

    private val _state = MutableLiveData<OnCallViewStates>()
    val state: LiveData<OnCallViewStates>
        get() = _state

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun startCall(userName: String) {
        Log.d(TAG, "STARTING CALL")
        _state.postValue(OnCallViewStates.CALL_RINGING)

        nexmoClient.call(userName, NexmoCallHandler.IN_APP, answerCallListener)
    }

    fun hangup() {
        CallManager.onGoingCall?.hangup(object : NexmoRequestListener<NexmoCall> {
            override fun onSuccess(p0: NexmoCall?) {
                _state.postValue(OnCallViewStates.CALL_ENDED)
            }

            override fun onError(p0: NexmoApiError) {
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
            _state.postValue(OnCallViewStates.CALL_ACCEPTED)
        }

        override fun onError(apiError: NexmoApiError) {
            _state.postValue(OnCallViewStates.CALL_ERROR)
        }
    }
}
