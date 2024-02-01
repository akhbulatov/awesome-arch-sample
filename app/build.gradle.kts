plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.awesomearchsample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.awesomearchsample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        buildConfig = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

// TODO https://github.com/google/dagger/issues/4049#issuecomment-1743321244
androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            // This is a workaround for https://issuetracker.google.com/301245705 which depends on internal
            // implementations of the android gradle plugin and the ksp gradle plugin which might change in the future
            // in an unpredictable way.
            project.tasks.getByName("ksp" + variant.name.capitalize() + "Kotlin") {
                val dataBindingTask =
                    project.tasks.getByName("dataBindingGenBaseClasses" + variant.name.capitalize())
                            as com.android.build.gradle.internal.tasks.databinding.DataBindingGenBaseClassesTask

                (this as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>).setSource(
                    dataBindingTask.sourceOutFolder
                )
            }
        }
    }
}

dependencies {
    implementation(project(":feature:main"))
    implementation(project(":feature:repo"))

    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")

    // AndroidX UI
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.activity:activity-ktx:1.8.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // DI
    implementation("com.google.dagger:hilt-android:2.50")
    ksp("com.google.dagger:hilt-android-compiler:2.50")

    // Async
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // Network
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // Navigation
    implementation("com.github.aartikov.Alligator:alligator:4.3.0")

    // Dev Tools
    implementation("com.jakewharton.timber:timber:5.0.1")
}