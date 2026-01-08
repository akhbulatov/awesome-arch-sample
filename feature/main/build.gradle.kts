plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.main"
}

dependencies {
    implementation(projects.core.ui)

    implementation(libs.serialization.core)
}
