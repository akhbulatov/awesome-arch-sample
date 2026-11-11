plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.launch"
}

dependencies {
    implementation(projects.core.commonApi)
    implementation(projects.core.ui)
    implementation(projects.domain)

    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.navigation3)
    implementation(libs.navigation3.ui)

    implementation(libs.serialization.core)
}
