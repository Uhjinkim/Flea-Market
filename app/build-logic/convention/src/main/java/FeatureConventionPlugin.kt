import org.gradle.api.Plugin
import org.gradle.api.Project

internal class FeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("dmart.plugin.android.library")
                apply("dmart.plugin.android.library.compose")
            }
        }
    }
}