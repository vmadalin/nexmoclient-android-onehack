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

object BuildAndroidConfig {
    const val APPLICATION_ID = "com.nexmo.onehack"

    const val BUILD_TOOLS_VERSION = "29.0.2"
    const val COMPILE_SDK_VERSION = 29
    const val MIN_SDK_VERSION = 23
    const val TARGET_SDK_VERSION = 29

    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"

    const val TEST_INSTRUMENTATION_RUNNER =
        "com.nexmo.onehack.runners.AndroidNexmoJUnitRunner"
}
