import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

@Suppress("unused")
class AwesomeAndroidApplicationConventionsPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("com.android.application")

        val minSdkVersion = providers.gradleProperty("minSdk").get().toInt()
        val compileSdkVersion = providers.gradleProperty("compileSdk").get().toInt()
        val targetSdkVersion = providers.gradleProperty("targetSdk").get().toInt()
        val versionCodeValue = providers.gradleProperty("versionCode").get().toInt()
        val versionNameValue = providers.gradleProperty("versionName").get()

        extensions.configure(ApplicationExtension::class.java) {
            compileSdk = compileSdkVersion

            defaultConfig {
                minSdk = minSdkVersion
                targetSdk = targetSdkVersion
                versionCode = versionCodeValue
                versionName = versionNameValue
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
    }
}
