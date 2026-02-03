import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import kotlinx.kover.gradle.plugin.dsl.KoverProjectExtension

@Suppress("unused")
class AwesomeKotlinJvmConventionsPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        pluginManager.apply("org.jetbrains.kotlinx.kover")

        extensions.configure(KotlinJvmProjectExtension::class.java) {
            jvmToolchain(17)
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_17)
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
