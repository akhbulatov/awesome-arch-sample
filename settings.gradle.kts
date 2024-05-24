pluginManagement {
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
include(":app")
include(":core:analytics")
include(":core:common")
include(":core:common-factory")
include(":core:common-impl")
include(":core:network")
include(":core:ui")
include(":domain")
include(":feature:common")
include(":feature:launch")
include(":feature:main")
include(":feature:repo")
include(":feature:user")
