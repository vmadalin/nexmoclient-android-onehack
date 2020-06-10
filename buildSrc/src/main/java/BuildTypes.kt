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

import org.gradle.api.NamedDomainObjectContainer

interface BuildType {
    val name: String
    val isMinifyEnabled: Boolean
}

object BuildDebug : BuildType {
    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-DEBUG"

    override val name = "debug"
    override val isMinifyEnabled = false

    fun <T> NamedDomainObjectContainer<T>.debug(configureAction: T.() -> Unit) {
        configureBuild(name, configureAction)
    }
}

object BuildRelease : BuildType {
    override val name = "release"
    override val isMinifyEnabled = true

    fun <T> NamedDomainObjectContainer<T>.release(configureAction: T.() -> Unit) {
        configureBuild(name, configureAction)
    }
}
