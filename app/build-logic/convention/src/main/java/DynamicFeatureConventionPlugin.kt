import com.android.build.api.dsl.DynamicFeatureExtension
import com.anotn.dmart.convension.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class DynamicFeatureConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.dynamic-feature")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            val extension = extensions.getByType<DynamicFeatureExtension>()
            configureAndroidCompose(extension)
        }
    }
}