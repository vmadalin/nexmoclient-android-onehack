## Nexmo-OneHack
[![CodeStyle](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://github.com/arturbosch/detekt)
[![Kotlin Version](https://img.shields.io/badge/kotlin-1.3.71-blue.svg)](http://kotlinlang.org/)
[![Gradle](https://img.shields.io/badge/gradle-6.2-blue.svg)](https://lv.binarybabel.org/catalog/gradle/latest)
[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=23)
[![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey.svg)](http://www.apache.org/licenses/LICENSE-2.0)

This repo contain the Nexmo project for the Vonage OneHack event.

## Development

### Environment setup

First off, you require the latest Android Studio 4.0.0 (or newer) to be able to build the app.

You need to have running the NPE (Nexmo Playground Environemnt) and use/create an application adding an user. The environment values should be specified on `gradle.properties` file with the following format:

```properties
# Nexmo environment variables for client android sdk:
nexmoSdkVersion=<insert>
nexmoEnvName=<insert>
nexmoRS256PrivateKey=<insert>
nexmoApplicationId=<insert>
nexmoUserName=<insert>
```

### Code style

To maintain the style and quality of the code, are used the bellow static analysis tools. All of them use properly configuration and you find them in the project root directory `.{toolName}`.

| Tools                                                   | Check command             | Fix command               |
|---------------------------------------------------------|---------------------------|---------------------------|
| [detekt](https://github.com/arturbosch/detekt)          | `./gradlew detekt`        | -                         |
| [spotless](https://github.com/diffplug/spotless)        | `./gradlew spotlessCheck` | `./gradlew spotlessApply` |
| [lint](https://developer.android.com/studio/write/lint) | `./gradlew lint`          | -                         |

All these tools are integrated in [pre-commit git hook](https://git-scm.com/book/en/v2/Customizing-Git-Git-Hooks), in order
ensure that all static analysis and tests passes before you can commit your changes. To skip them for specific commit add this option at your git command:

```properties
git commit --no-verify
```

The pre-commit git hooks are defined a script. This step ensures that all commits comply with the established rules. However the continuous integration will ultimately be validated that the changes are correct.

## Tech-stack

This project takes advantage of many popular libraries, plugins and tools of the Android ecosystem. Most of the libraries are in the stable version, unless there is a good reason to use non-stable dependency.

### Dependencies

-   [Jetpack](https://developer.android.com/jetpack):
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    -   [AndroidX](https://developer.android.com/jetpack/androidx) - major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - allows you to bind UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [Navigation](https://developer.android.com/guide/navigation/) - helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
-   [GSON](https://github.com/google/gson) - java serialization/deserialization library to convert Java Objects into JSON and back.
-   [JJWT](https://github.com/jwtk/jjwt) - java JWT: JSON Web Token for Java and Android.

### Plugins

-   [Detekt](https://github.com/arturbosch/detekt) - a static code analysis tool for the Kotlin programming language.
-   [Spotless](https://github.com/diffplug/spotless) - a code formatter can do more than just find formatting errors.
-   [Versions](https://github.com/ben-manes/gradle-versions-plugin) - make easy to determine which dependencies have updates.

## License

```license
Copyright (C) 2020 Nexmo, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
