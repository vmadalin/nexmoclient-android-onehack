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

plugins {
    id("com.android.library")
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
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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

dependencies {
    implementation(BuildDependencies.AndroidX.APPCOMPAT)
    implementation(BuildDependencies.AndroidX.CORE_KTX)
    implementation(BuildDependencies.AndroidX.LIFECYCLE_EXTENSIONS)
    implementation(BuildDependencies.AndroidX.LIFECYCLE_VIEWMODEL)
    implementation(BuildDependencies.Kotlin.STDLIB)
}
