plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.preferences"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.datastore)
}
