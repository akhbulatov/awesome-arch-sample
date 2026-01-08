plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.analytics"
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.kermit)
}
