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
include(":core:common")
include(":core:network")
include(":core:ui")
include(":domain")
include(":feature:launch")
include(":feature:main")
include(":feature:repo")
