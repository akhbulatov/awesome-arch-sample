import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class AwesomeAndroidFeatureConventionsPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("awesome.android.library.conventions")
        pluginManager.apply("org.jetbrains.kotlin.plugin.compose")
        pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

        extensions.configure(LibraryExtension::class.java) {
            buildFeatures {
                buildConfig = true
                compose = true
            }
        }
    }
}
