plugins {
    id("awesome.android.library.conventions")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.awesomearchsample.data"
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
}

dependencies {
    implementation(projects.core.coreFactory)
    implementation(projects.core.network)
    implementation(projects.core.preferences)
    implementation(projects.domain)

    implementation(libs.ktor.client.core)
    implementation(libs.serialization.json)

    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}
