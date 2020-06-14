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

buildscript {
    repositories.applyDefault()

    dependencies {
        classpath(BuildDependencies.Android.TOOLS_PLUGIN)
        classpath(BuildDependencies.AndroidX.NAVIGATION_ARGS_PLUGIN)
        classpath(BuildDependencies.Kotlin.GRADLE_PLUGIN)
        classpath(BuildDependencies.Kotlin.ALLOPEN_PLUGIN)
    }
}

allprojects {
    repositories.applyDefault()

    apply(BuildScripts.GIT_HOOKS)
    apply(BuildScripts.VERSIONS)
    apply(BuildScripts.DETEKT)
    apply(BuildScripts.SPOTLESS)
}
