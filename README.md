<h1 align="center">Phood - Discover Recipes</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.xxx-a97bff"/></a>
  <img alt="Clean Architecture" src="https://img.shields.io/badge/Clean-Architecture-white"/>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

<p align="center">  
🍕 Phood: Is a modern Android app with Hilt, Coroutines, Flow, RestApi, Jetpack (Room, ViewModel), Testing (JUint5), and Material Design based on Clean architecture and MVVM architecture. And also apply Modularization.
</p>
</br>

<p align="center">
<img src="https://github.com/MoatazBadawy/Phood/assets/63272288/0373493d-0055-4c7a-bd9d-769440ce1552"/>
</p>

## App Story 
The application contains many branches, each branch contains a specific feature. For every branch, I made a Pull Request to explain the new feature that was added.
I have not currently integrated any branch with the develop branch and left all the PRs open as is, to facilitate review of the code through the reviewer.

## About

This version of the application has a lot of features, go to the [Releases](https://github.com/MoatazBadawy/Mawaqeet-Todo_and_Habits/releases) to know what's new. 

https://github.com/MoatazBadawy/Phood/assets/63272288/77cd21e7-d315-46fb-9508-ed3c569df4db

#### Project Architecture
This project follows the Clean Architecture structure and MVVM. The domain layer contains UseCases that encapsulate a single, specific task that is part of the application's business logic. The data layer implements the repository interface defined in the domain layer, providing a single source of truth for data. The UI layer uses all the components and classes related to the Android framework to get the data from the ViewModel layer and display it on the UI.

<img src="https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png" width="500" />

#### Structure (App Modules)
This project is use modularizing by feature. Every feature has it is one (data - domain - UI)

      + App <- The main module, handles hilt providers 
      + recipes/
          + data <- implements the repository interface defined in the domain layer
            - local 
            - repositories
          + domain <- contains UseCases that encapsulate the business logic.
            - entities 
            - repository
            - usecases
          + UI <- uses MVVM with ViewModels exposing StateFlow that the UI consumes.
              - view
              - viewmodel
      + identity <- Have the same things as habits module

#### Data and Dependenciy Flow:
This illustration from the clean architecture book shows the dependencies between the layers in an example app and the way data flows between them. (our app uses the same thing).

<img src="https://user-images.githubusercontent.com/63272288/224540200-813c1fd2-1416-4f2a-b404-ac9dc93b655f.jpg" width="500" />
              

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
  - StateFlow: For reactive style programming (from VM to UI). 
  - DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
  - Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
  - [Hilt](https://dagger.dev/hilt/): for dependency injection.
  - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) - Used to navigate between fragments
  - [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
- Architecture
  - Clean Architecture (Data - Domain - UI)
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
  - Repository Pattern
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Used to handle gradle dependencies and config versions
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Junit5](https://junit.org/junit5/): Serves as a foundation for launching testing frameworks on the JVM.
- [Coil](https://coil-kt.github.io/coil/): An image loading library for Android backed by Kotlin Coroutines.

## TODO
- [X] Recipes Screen
- [X] Recipe Details Screen
- [X] Recipes Favourites Screen
- [X] Search for Recipes Screen
