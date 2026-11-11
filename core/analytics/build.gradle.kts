plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.analytics"
}

dependencies {
    api(projects.core.commonApi)

    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
}
