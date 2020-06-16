package com.nexmo.onehack.features.calls.outgoing


import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoCall
import com.nexmo.client.NexmoCallHandler
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoApiError
import com.nexmo.client.request_listener.NexmoRequestListener
import com.nexmo.utils.logger.Log


class OutgoingCallViewModel(
    private val nexmoClient: NexmoClient,
    private val nexmoCall: NexmoCall?
) : ViewModel() {
    private val TAG = OutgoingCallViewModel::class.java.name

    val calleeName = nexmoCall?.callMembers?.first()?.user?.name ?: run { "UNKNOWN" }

    private val _state = MutableLiveData<OutgoingCallViewStates>()
    val state: LiveData<OutgoingCallViewStates>
        get() = _state

    fun startCall() {
        Log.d(TAG, "STARTING CALL")
        _state.postValue(OutgoingCallViewStates.CALL_RINGING)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }

        NexmoClient.get().call(
            "username/phone number",
            NexmoCallHandler.IN_APP / NexmoCallHandler.SERVER,
            object : NexmoRequestListener<NexmoCall?> {
                override fun onSuccess(call: NexmoCall?) {
                    // Update the application UI here if needed.
                    // Got the NexmoCall object
                }

                fun onError(error: NexmoAPIError?) {
                    // Update the application UI here if needed.
                }
            })
    }

    fun endCall() {
        Log.d(TAG, "ENDING CALL")
        _state.postValue(OutgoingCallViewStates.CALL_ENDED)
    }

    private val answerCallListener = object : NexmoRequestListener<NexmoCall> {
        override fun onSuccess(call: NexmoCall?) {
            _state.postValue(OutgoingCallViewStates.CALL_ACCEPTED)
        }

        override fun onError(apiError: NexmoApiError) {
            _state.postValue(OutgoingCallViewStates.CALL_ERROR)
        }
    }
}
