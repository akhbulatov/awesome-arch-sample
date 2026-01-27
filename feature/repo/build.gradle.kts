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

    testImplementation(libs.junit4)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.robolectric)
    testImplementation(projects.core.testing)
}
