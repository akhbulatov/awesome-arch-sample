plugins {
    id("awesome.android.application.conventions")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.awesomearchsample"

    defaultConfig {
        applicationId = "com.example.awesomearchsample"
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
    implementation(projects.feature.user)
}
