plugins {
    id("awesome.android.library.conventions")
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.awesomearchsample.core.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.serialization)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.contentNegotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.json)
}
