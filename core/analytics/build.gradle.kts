plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.analytics"
}

dependencies {
    implementation(projects.core.commonApi)

    implementation(libs.kermit)

    testImplementation(libs.junit4)
    testImplementation(libs.robolectric)
}
