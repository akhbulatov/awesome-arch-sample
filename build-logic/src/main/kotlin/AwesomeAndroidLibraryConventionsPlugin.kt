import com.android.build.api.dsl.LibraryExtension
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AwesomeAndroidLibraryConventionsPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.library")
        pluginManager.apply("org.jetbrains.kotlinx.kover")

        val minSdkVersion = providers.gradleProperty("minSdk").get().toInt()
        val compileSdkVersion = providers.gradleProperty("compileSdk").get().toInt()

        extensions.configure(LibraryExtension::class.java) {
            compileSdk = compileSdkVersion

            defaultConfig {
                minSdk = minSdkVersion
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
                create("beta") {
                    initWith(getByName("release"))
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }
        }

        extensions.configure(KotlinAndroidProjectExtension::class.java) {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_21)
            }
        }

        extensions.configure(KoverProjectExtension::class.java) {
            reports {
                filters {
                    excludes {
                        classes(
                            "*.BuildConfig",
                            "*.R",
                            "*.R$*",
                            "*Manifest*"
                        )
                    }
                }
            }
        }
    }
}
