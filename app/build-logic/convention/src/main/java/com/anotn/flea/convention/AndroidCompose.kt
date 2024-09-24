package com.anotn.flea.convention

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal fun Project.configureAndroidCompose(
    commonExtension: BaseExtension,
) {
    commonExtension.apply {
        buildFeatures.apply { compose = true }
        with(pluginManager) {
            apply("org.jetbrains.kotlin.plugin.compose")
        }
        val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
        dependencies {
            add("implementation", libs.findLibrary("androidx-compose-bom").get())
            add("implementation", libs.findBundle("compose-bundle").get())
            add("debugImplementation", libs.findBundle("compose-debug-bundle").get())

        }
    }
}