pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "AwesomeArchSample"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":core:analytics")
include(":core:common")
include(":core:common-impl")
include(":core:core-factory")
include(":core:network")
include(":core:preferences")
include(":core:serialization")
include(":core:ui")
include(":data")
include(":domain")
include(":feature:common")
include(":feature:launch")
include(":feature:main")
include(":feature:repo")
include(":feature:search")
include(":feature:user")
