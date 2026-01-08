plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.serialization"
}

dependencies {
    api(libs.serialization.json)
}
