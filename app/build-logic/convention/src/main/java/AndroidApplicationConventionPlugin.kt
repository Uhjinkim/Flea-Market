import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.anotn.flea.convention.configureAndroidCompose
import com.anotn.flea.convention.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<BaseAppModuleExtension> {
                configureKotlinAndroid(this)
            }
            val extension = extensions.getByType<BaseExtension>()
            configureAndroidCompose(extension)
            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("implementation", libs.findLibrary("hilt-android").get())
                add("compileOnly", libs.findLibrary("hilt-compiler").get())
                add("implementation", libs.findLibrary("timber").get())
            }

        }
    }
}