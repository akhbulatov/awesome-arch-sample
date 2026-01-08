plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibraryConventions") {
            id = "awesome.android.library.conventions"
            implementationClass = "AwesomeAndroidLibraryConventionsPlugin"
        }
        register("kotlinJvmConventions") {
            id = "awesome.kotlin.jvm.conventions"
            implementationClass = "AwesomeKotlinJvmConventionsPlugin"
        }
    }
}
