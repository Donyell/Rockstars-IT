# Rockstars-IT Assignment - Donyell Margaret

In this app you can find an overview of your favorite rockstars. Using the search bar at the top, you can quickly search for your favorite artist. In the detail screen there is a list with all the songs performed by the artist.

## Architecture
This app uses the principles of Clean Architecture which makes it possible to create maintainable, testable and scalable applications.

The app is divided into four modules:

* `app`: Makes use of the Android framework and is used to show the UI on the screen. 
* `presenation`: Fetches data from the `domain` and emits it in the optimal UI format.
* `domain`: Contains the business logic and use cases of the app.
* `data`: Fetches data from the external API.


## Requirements
* JDK 1.8
* [Android SDK](https://developer.android.com/studio/index.html)
* Android 11 ([API 30](https://developer.android.com/preview/api-overview.html))
* Latest [Android SDK Tools](https://developer.android.com/studio) and [Android build tools](https://developer.android.com/studio/releases/build-tools)
* [JSON Server](https://github.com/typicode/json-server)

## Libraries 
* [Kotlin](https://kotlinlang.org/)
* [RxJava2](https://github.com/ReactiveX/RxJava/wiki/What's-different-in-2.0)
* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* [Retrofit](http://square.github.io/retrofit/)
* [OkHttp](http://square.github.io/okhttp/)
* [Gson](https://github.com/google/gson)
* [Mockito](http://site.mockito.org/)
* [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin)
* [JUnit](https://junit.org/junit5/docs/current/user-guide/)
* [LiveData Testing](https://github.com/jraska/livedata-testing)

## Getting started
Start JSON Server 
```
json-server https://www.teamrockstars.nl/sites/default/files/db.json
```

If you're testing on a physical device, change the URL in [NetworkModule](https://github.com/Donyell/Rockstars-IT/blob/main/app/src/main/java/nl/donyell/rockstars_it/di/NetworkModule.kt) to your computer's local IP address as explained [here](https://stackoverflow.com/a/61479138).

## TODO
* Non-hardcoded server URL.
* `Normal`, `Loading`, `Error` and `Empty` UI state.
* UI tests
