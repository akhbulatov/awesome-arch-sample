plugins {
    id("awesome.android.library.conventions")
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room3)
}

android {
    namespace = "com.example.awesomearchsample.data"
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.core.preferences)
    implementation(projects.domain)

    implementation(platform(libs.ktor.bom))
    implementation(libs.ktor.client.core)

    implementation(libs.serialization.json)

    implementation(libs.room3.runtime)
    ksp(libs.room3.compiler)

    testImplementation(libs.junit4)
}
