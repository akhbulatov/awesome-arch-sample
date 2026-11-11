plugins {
    id("awesome.android.application.conventions")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.awesomearchsample"

    defaultConfig {
        applicationId = "com.example.awesomearchsample"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "NETWORK_API_BASE_URL", "\"https://api.github.com/\"")
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.coreFactory)
    implementation(projects.core.network)
    implementation(projects.core.preferences)
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

    implementation(libs.activity)
    implementation(libs.activity.compose)
    implementation(libs.lifecycle.viewmodel.navigation3)
    implementation(libs.navigation3.ui)

    androidTestImplementation(projects.core.testing)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.coroutines.core)
    debugImplementation(libs.compose.ui.test.manifest)
}
