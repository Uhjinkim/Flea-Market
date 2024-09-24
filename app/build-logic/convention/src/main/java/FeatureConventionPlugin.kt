import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.anotn.flea.convention.configureAndroidCompose
import com.anotn.flea.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("convention.android.library")
                apply("convention.hilt")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
            }

            val extension = extensions.getByType<BaseExtension>()
            configureAndroidCompose(extension)
            val lib = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", (lib.findLibrary("hilt-android").get()))
                add("ksp",(lib.findLibrary("hilt-compiler").get()))
            }

        }
    }
}