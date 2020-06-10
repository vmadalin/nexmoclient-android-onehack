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

object BuildDependencies {

    const val SPOTLESS_PLUGIN = "com.diffplug.spotless:spotless-plugin-gradle:3.28.0"
    const val VERSIONS_PLUGIN = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
    const val NEXMO = "com.nexmo.android:client-sdk:"

    object Android {
        const val TOOLS_PLUGIN = "com.android.tools.build:gradle:4.0.0"
    }

    object AndroidX {
        private const val LIFECYCLE_VERSION = "2.2.0"
        const val LIFECYCLE_EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$LIFECYCLE_VERSION"
        const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VERSION"

        private const val NAVIGATION_VERSION = "2.2.2"
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION_VERSION"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION_VERSION"

        const val APPCOMPAT = "androidx.appcompat:appcompat:1.1.0"
        const val CORE_KTX = "androidx.core:core-ktx:1.3.0"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:1.1.3"
    }

    object Detekt {
        private const val VERSION = "1.9.1"
        const val PLUGIN = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:$VERSION"
        const val FORMATTING = "io.gitlab.arturbosch.detekt:detekt-formatting:$VERSION"
    }

    object Google {
        const val GSON = "com.google.code.gson:gson:2.8.6"
    }

    object Kotlin {
        private const val VERSION = "1.3.72"
        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$VERSION"
        const val GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:$VERSION"
        const val ALLOPEN_PLUGIN = "org.jetbrains.kotlin:kotlin-allopen:$VERSION"
    }

    object JJWT {
        private const val VERSION = "0.11.1"
        const val API = "io.jsonwebtoken:jjwt-api:$VERSION"
        const val IMPL = "io.jsonwebtoken:jjwt-impl:$VERSION"
        const val ORGJSON = "io.jsonwebtoken:jjwt-orgjson:$VERSION"
    }

    object Test {
        private const val VERSION = "1.2.0"
        const val CORE = "androidx.test:core:$VERSION"
        const val RUNNER = "androidx.test:runner:$VERSION"
        const val RULES = "androidx.test:rules:$VERSION"
        const val FRAGMENT_TESTING = "androidx.fragment:fragment-testing:$VERSION"

        const val JUNIT = "junit:junit:4.12"
        const val EXT_JUNIT = "androidx.test.ext:junit:1.1.1"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.2.0"
    }
}
