import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val lib = extensions.getByType<VersionCatalogsExtension>().named("libs")
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("com.google.dagger.hilt.android")
            }
            dependencies {
                add("implementation", (lib.findLibrary("hilt-android").get()))
                add("implementation", (lib.findLibrary("hilt-navigation-compose").get()))
                add("ksp",(lib.findLibrary("hilt-compiler").get()))
            }
        }
    }
}