plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.common"
}

dependencies {
    implementation(libs.coroutines.core)

    implementation(libs.kermit)
}
