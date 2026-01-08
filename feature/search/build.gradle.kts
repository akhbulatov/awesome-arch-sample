plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.search"
}

dependencies {
    implementation(projects.core.coreFactory)
    implementation(projects.core.ui)
    implementation(projects.domain)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)
}
