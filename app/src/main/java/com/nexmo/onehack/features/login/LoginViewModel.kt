/*
 * Copyright (C) 2020 Nexmo, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nexmo.onehack.features.login

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nexmo.client.NexmoClient
import com.nexmo.client.request_listener.NexmoConnectionListener.ConnectionStatus
import com.nexmo.base.android.annotations.OpenForTesting
import com.nexmo.onehack.models.NexmoEnvironment
import com.nexmo.helper.jwt.JWTHelper
import java.lang.reflect.Modifier.PRIVATE

@OpenForTesting
class LoginViewModel(
    private val nexmoClient: NexmoClient,
    private val nexmoEnvironment: NexmoEnvironment,
    private val jwtHelper: JWTHelper
) : ViewModel() {

    private val _state = MutableLiveData<LoginViewState>()
    val state: LiveData<LoginViewState>
        get() = _state

    init {
        nexmoClient.setConnectionListener { connectionStatus, _ ->
            when (connectionStatus) {
                ConnectionStatus.CONNECTING -> _state.postValue(LoginViewState.CONNECTING)
                ConnectionStatus.CONNECTED -> _state.postValue(LoginViewState.CONNECTED)
                ConnectionStatus.DISCONNECTED -> _state.postValue(LoginViewState.DISCONNECTED)
                else -> _state.postValue(LoginViewState.UNKNOWN)
            }
        }
    }

    fun loginUser() {
        generateUserToken().run {
            nexmoClient.login(this)
        }
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun generateUserToken(userName: String? = nexmoEnvironment.username): String =
        jwtHelper.generateToken(
            privateKey = nexmoEnvironment.rs256PrivateKey,
            applicationId = nexmoEnvironment.applicationId,
            username = userName
        )
}
