# CatSearchApp
Demo App that uses Api calls and graph search algorithm to locate a point on a xy plane.
- Programming language: Kotlin
- Build tool: gradle 6.3

## Architecture
Separated the code in into Api layer, Network layer and Business layer allowing me to easily test components independently. The entrypoint to the application
App.kt calls SearchClient class which interacts and makes calls to the network and also contains the algorithm that calculates the location based on directions 
obtain from the api. Usedretrofit to mange the api calls and used execute() function to get the response synchronously. 

## Dependency Injection
Used Dependency injection to reduce coupling and simplify testing.
For dependency injection I used Koin. A service locator framework that is much simpler and does not require
code generation and complex set up like dagger.

## Testing
Wrote unit tests covering all the scenarios in my Business and Network layer.
I used the standard Mockito and Junit testing framework. Also used 'com.nhaarman.mockitokotlin2:mockito' a framework that provides useful
helper functions for working with Mockito in Kotlin

`./gradlew test`

## Build And Run
To build and run the application you can used `./gradlew run` and `./gradlew build` from the terminal. Or use an IDE like Intellij

