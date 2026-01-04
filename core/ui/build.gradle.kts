import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.awesomearchsample.core.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
}

dependencies {
    implementation(projects.core.common)

    api(libs.activity)
    api(libs.activity.compose)

    api(platform(libs.compose.bom))
    api(libs.compose.foundation)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling)
    api(libs.compose.ui.tooling.preview)

    api(libs.lifecycle.runtime.compose)
    api(libs.lifecycle.viewmodel.compose)
    api(libs.lifecycle.viewmodel.ktx)
    api(libs.lifecycle.viewmodel.navigation3)

    api(libs.navigation3.runtime)
    api(libs.navigation3.ui)
}
