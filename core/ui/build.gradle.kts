plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger.hilt.android)
}

android {
    namespace = "com.example.awesomearchsample.core.ui"
    compileSdk = libs.versions.compilesdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minsdk.get().toInt()
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
    implementation(project(":core:common-factory"))

    api(libs.activity)
    api(libs.activity.compose)

    api(platform(libs.compose.bom))
    api(libs.compose.foundation)
    api(libs.compose.material3)
    api(libs.compose.runtime)
    api(libs.compose.ui)

    api(libs.lifecycle.viewmodel.ktx)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    api(libs.voyager.navigator)
    api(libs.voyager.hilt)
}