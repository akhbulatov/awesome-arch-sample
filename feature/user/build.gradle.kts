plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.example.awesomearchsample.feature.user"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minsdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtension.get()
    }
}

dependencies {
    implementation(project(":core:common-factory"))
    implementation(project(":core:network"))
    api(project(":core:ui"))
    implementation(project(":domain"))

    implementation(libs.coil.compose)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    implementation(libs.ktor.client.core)
    implementation(libs.serialization.json)
}