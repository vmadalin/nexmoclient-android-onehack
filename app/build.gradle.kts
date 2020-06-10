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

import BuildRelease.release
import BuildDebug.debug
import com.android.build.gradle.internal.dsl.BuildType

val nexmoSdkVersion: String by project
val nexmoEnvName: String by project
val nexmoRS256PrivateKey: String by project
val nexmoApplicationId: String by project
val nexmoUserName: String by project

object Constants {
    const val ONE_TR = "no_wallets.json"
}

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("kotlin-allopen")
}

allOpen {
    annotation("com.nexmo.base.android.annotations.OpenClass")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        release {
            isMinifyEnabled = BuildRelease.isMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
            buildConfigStringField("NEXMO_API_URL", "https://api.nexmo.com")
            buildConfigStringField("NEXMO_WEBSOCKET_URL", "https://ws.nexmo.com")
        }
        debug {
            isMinifyEnabled = BuildDebug.isMinifyEnabled
            applicationIdSuffix = BuildDebug.applicationIdSuffix
            versionNameSuffix = BuildDebug.versionNameSuffix

            buildConfigStringField("NEXMO_API_URL", "https://$nexmoEnvName-api.npe.nexmo.io")
            buildConfigStringField("NEXMO_WEBSOCKET_URL", "https://$nexmoEnvName-ws.npe.nexmo.io")
        }
    }

    buildTypes.forEach {
        it.buildConfigStringField("NEXMO_IPS_URL", "https://api.dev.nexmoinc.net/play4/v1/image")
        it.buildConfigStringField("NEXMO_ENV_NAME", nexmoEnvName)
        it.buildConfigStringField("NEXMO_RS256_PRIVATE_KEY", nexmoRS256PrivateKey)
        it.buildConfigStringField("NEXMO_APPLICATION_ID", nexmoApplicationId)
        it.buildConfigStringField("NEXMO_USERNAME", nexmoUserName)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures{
        dataBinding = true
    }

    lintOptions {
        setLintConfig(rootProject.file(".lint/config.xml"))
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }
}

tasks {
    "preBuild" {
        dependsOn(":installGitHooks")
    }
}

dependencies {
    implementation(project(":base-android"))
    implementation(project(":helper-jwt"))
    implementation(BuildDependencies.Kotlin.STDLIB)
    implementation(BuildDependencies.AndroidX.APPCOMPAT)
    implementation(BuildDependencies.AndroidX.CORE_KTX)
    implementation(BuildDependencies.AndroidX.CONSTRAINT_LAYOUT)
    implementation(BuildDependencies.AndroidX.NAVIGATION_FRAGMENT)
    implementation(BuildDependencies.AndroidX.NAVIGATION_UI)
    implementation(BuildDependencies.NEXMO + nexmoSdkVersion)

    debugImplementation(BuildDependencies.Test.FRAGMENT_TESTING)

    testImplementation(BuildDependencies.Test.JUNIT)

    androidTestImplementation(BuildDependencies.Test.CORE)
    androidTestImplementation(BuildDependencies.Test.RUNNER)
    androidTestImplementation(BuildDependencies.Test.RULES)
    androidTestImplementation(BuildDependencies.Test.EXT_JUNIT)
    androidTestImplementation(BuildDependencies.Test.ESPRESSO_CORE)
}

fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}
