plugins {
    id("awesome.android.library.conventions")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.awesomearchsample.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.commonApi)

    api(platform(libs.compose.bom))
    api(libs.compose.runtime)
    api(libs.coroutines.core)
    api(libs.lifecycle.viewmodel)
    api(libs.navigation3.runtime)

    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
}
