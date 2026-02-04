plugins {
    id("awesome.android.library.conventions")
}

android {
    namespace = "com.example.awesomearchsample.core.testing"
}

dependencies {
    implementation(projects.core.common)
    implementation(libs.coroutines.test)
    implementation(libs.junit4)
}
