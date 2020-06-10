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

package com.nexmo.helper.jwt.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Path(
    @SerializedName("/*/users/**")
    val users: JsonObject = JsonObject(),
    @SerializedName("/*/conversations/**")
    val conversations: JsonObject = JsonObject(),
    @SerializedName("/*/sessions/**")
    val sessions: JsonObject = JsonObject(),
    @SerializedName("/*/devices/**")
    val devices: JsonObject = JsonObject(),
    @SerializedName("/*/image/**")
    val image: JsonObject = JsonObject(),
    @SerializedName("/*/media/**")
    val media: JsonObject = JsonObject(),
    @SerializedName("/*/applications/**")
    val applications: JsonObject = JsonObject(),
    @SerializedName("/*/push/**")
    val push: JsonObject = JsonObject(),
    @SerializedName("/*/knocking/**")
    val knocking: JsonObject = JsonObject(),
    @SerializedName("/*/calls/**")
    val calls: JsonObject = JsonObject(),
    @SerializedName("/*/legs/**")
    val legs: JsonObject = JsonObject()
)
