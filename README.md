<h1 align="center">Phood - Discover Recipes</h1>

<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://kotlinlang.org"><img alt="Kotlin" src="https://img.shields.io/badge/Kotlin-1.8.xxx-a97bff"/></a>
  <img alt="Clean Architecture" src="https://img.shields.io/badge/Clean-Architecture-white"/>
  <img alt="MVVM" src="https://img.shields.io/badge/MVVM-Architecture-orange"/>
</p>

<p align="center">  
🍕 Phood: Is a modern Android app with Hilt, Coroutines, Flow, RestApi, Jetpack (Room, ViewModel), and Material Design based on Clean architecture and MVVM architecture. And also apply Modularization.
</p>
</br>

<p align="center">
<img src="https://github.com/MoatazBadawy/Phood/assets/63272288/6c81806c-ffcc-468a-ace1-731519e193f2"/>
</p>

## App Story 
The application contains many branches, each branch contains a specific feature. For every branch, I made a Pull Request to explain the new feature that was added.
I have not currently merged any branch with the develop branch and left all the PRs open as is, to facilitate review of the code through the reviewer.

## About
This version of the application has a lot of features, plz go to the [Releases](https://github.com/MoatazBadawy/Phood/releases) to know what's new. 

https://github.com/MoatazBadawy/Phood/assets/63272288/0123b3f4-73b8-48a8-8882-28ba034afea2

#### Project Architecture
This project follows the Clean Architecture structure and MVVM. The domain layer contains UseCases that encapsulate a single, specific task that is part of the application's business logic. The data layer implements the repository interface defined in the domain layer, providing a single source of truth for data. The UI layer uses all the components and classes related to the Android framework to get the data from the ViewModel layer and display it on the UI.

<img src="https://koenig-media.raywenderlich.com/uploads/2019/06/Clean-Architecture-graph.png" width="500" />

#### Structure (App Modules)
This project uses modularizing by feature. Every feature has it is one (data - domain - UI)

      + App <- The main module, handles hilt providers 
      + recipes/
          + data <- implements the repository interface defined in the domain layer
            - local 
            - remote
            - repositories
          + domain <- contains UseCases that encapsulate the business logic.
            - entities 
            - repository
            - usecases
          + UI <- uses MVVM with ViewModels exposing StateFlow that the UI consumes.
              - view
              - viewmodel
      + identity <- Have the same things as recipes module

#### Data and Dependenciy Flow:
This illustration from the clean architecture book shows the dependencies between the layers in an example app and the way data flows between them. (our app uses the same thing).

<img src="https://user-images.githubusercontent.com/63272288/224540200-813c1fd2-1416-4f2a-b404-ac9dc93b655f.jpg" width="500" />
              

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- Jetpack
  - Lifecycle: Observe Android lifecycles and handle UI states upon the lifecycle changes.
  - ViewModel: Manages UI-related data holder and lifecycle awareness. Allows data to survive configuration changes such as screen rotations.
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
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Construct the REST APIs and paging network data.
- [Media3](https://developer.android.com/guide/topics/media/media3): New home for media libraries that enables Android apps to display rich audio and visual experiences.
- [Coil](https://coil-kt.github.io/coil/): An image loading library for Android backed by Kotlin Coroutines.
- [Junit5](https://junit.org/junit5/): Serves as a foundation for launching testing frameworks on the JVM.
- [Kotlin-DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - Used to handle gradle dependencies and config versions
- [ksp](https://github.com/google/ksp): Kotlin Symbol Processing API.

## TODO
- [X] Recipes Screen
- [X] Recipe Details Screen
- [X] Recipes Favourites Screen
- [X] Search for Recipes Screen
- [X] Offline Caching
- [ ] Migrate to KMM

## License
```XML
Designed and developed by 2023 Moataz Mohamed

Licensed under the Apache License, Version 2.0 (the "License");
You may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
