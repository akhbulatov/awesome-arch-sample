plugins {
    id("awesome.android.application.conventions")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.awesomearchsample"

    defaultConfig {
        applicationId = "com.example.awesomearchsample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.core.coreFactory)
    implementation(projects.core.ui)
    implementation(projects.domain)
    implementation(projects.data)
    implementation(projects.feature.common)
    implementation(projects.feature.launch)
    implementation(projects.feature.main)
    implementation(projects.feature.repo)
    implementation(projects.feature.search)
    implementation(projects.feature.settings)
    implementation(projects.feature.user)

    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    debugImplementation(libs.compose.ui.test.manifest)
}
