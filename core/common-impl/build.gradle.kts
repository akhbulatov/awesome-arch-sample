plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.commonimpl"
}

dependencies {
    implementation(projects.core.commonApi)
    implementation(projects.core.network)
    implementation(projects.core.serialization)

    implementation(libs.ktor.client.core)
}
