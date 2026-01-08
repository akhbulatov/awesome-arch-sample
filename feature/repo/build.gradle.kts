plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.repo"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.coreFactory)
    implementation(projects.core.ui)
    implementation(projects.domain)
    implementation(projects.feature.common)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)
}
