plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.main"
}

dependencies {
    implementation(projects.core.ui)

    implementation(libs.lifecycle.viewmodel.navigation3)
    implementation(libs.navigation3.ui)

    implementation(libs.serialization.core)
}
