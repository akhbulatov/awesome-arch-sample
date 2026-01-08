plugins {
    id("awesome.android.feature.conventions")
}

android {
    namespace = "com.example.awesomearchsample.feature.common"
}

dependencies {
    implementation(projects.core.analytics)
    implementation(projects.core.coreFactory)
    implementation(projects.domain)

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.runtime)
}
