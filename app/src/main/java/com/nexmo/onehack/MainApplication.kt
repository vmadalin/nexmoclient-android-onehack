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

package com.nexmo.onehack

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.nexmo.client.NexmoClient
import com.nexmo.utils.logger.ILogger
import com.nexmo.base.android.annotations.OpenForTesting
import com.nexmo.onehack.models.NexmoEnvironment
import java.lang.reflect.Modifier.PRIVATE

@OpenForTesting
class MainApplication : Application() {

    companion object {
        lateinit var nexmoEnvironment: NexmoEnvironment
    }

    override fun onCreate() {
        super.onCreate()
        initNexmoEnvironment()
        initNexmoClient()
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun initNexmoEnvironment() {
        nexmoEnvironment = NexmoEnvironment(
            envName = BuildConfig.NEXMO_ENV_NAME,
            rs256PrivateKey = BuildConfig.NEXMO_RS256_PRIVATE_KEY,
            applicationId = BuildConfig.NEXMO_APPLICATION_ID,
            username = BuildConfig.NEXMO_USERNAME
        )
    }

    @VisibleForTesting(otherwise = PRIVATE)
    fun initNexmoClient() {
        NexmoClient.Builder()
            .logLevel(ILogger.eLogLevel.SENSITIVE)
            .logKey(0x0L)
            .restEnvironmentHost(BuildConfig.NEXMO_API_URL)
            .environmentHost(BuildConfig.NEXMO_WEBSOCKET_URL)
            .build(this)
    }
}
