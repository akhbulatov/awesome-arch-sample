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
    implementation(projects.core.common)

    api(libs.activity)
    api(libs.activity.compose)

    api(platform(libs.compose.bom))
    api(libs.compose.foundation)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.tooling.preview)

    api(libs.lifecycle.runtime.compose)
    api(libs.lifecycle.viewmodel.compose)
    api(libs.lifecycle.viewmodel.ktx)
    api(libs.lifecycle.viewmodel.navigation3)

    api(libs.navigation3.runtime)
    api(libs.navigation3.ui)
}
