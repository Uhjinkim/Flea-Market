import com.android.build.api.dsl.DynamicFeatureExtension
import com.android.build.gradle.BaseExtension
import com.anotn.flea.convention.configureKotlinAndroid
import com.anotn.flea.convention.configureAndroidCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType

class DynamicFeatureConventionPlugin : Plugin<Project>{
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.dynamic-feature")
            }
            extensions.configure<DynamicFeatureExtension> {
                configureKotlinAndroid(this)
            }
            val extension = extensions.getByType<BaseExtension>()
            configureAndroidCompose(extension)
        }
    }
}