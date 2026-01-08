plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.corefactory"
}

dependencies {
    implementation(projects.core.analytics)
    api(projects.core.common)
    implementation(projects.core.commonImpl)
    implementation(projects.core.network)
    implementation(projects.core.preferences)
    implementation(projects.core.serialization)
    implementation(projects.core.ui)
}
