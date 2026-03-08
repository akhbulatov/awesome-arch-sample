plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.user"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.domain)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.serialization.core)

    testImplementation(libs.junit4)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(projects.core.commonApi)
    testImplementation(projects.core.testing)
}
