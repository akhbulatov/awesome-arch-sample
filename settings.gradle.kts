pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
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
include(":core:common-api")
include(":core:common-impl")
include(":core:core-factory")
include(":core:network")
include(":core:preferences")
include(":core:serialization")
include(":core:testing")
include(":core:ui")
include(":data")
include(":domain")
include(":feature:common")
include(":feature:launch")
include(":feature:main")
include(":feature:repo")
include(":feature:search")
include(":feature:settings")
include(":feature:user")
