plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "dmart.plugin.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "dmart.plugin.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "dmart.plugin.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = "dmart.plugin.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("feature") {
            id = "dmart.plugin.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("hilt") {
            id = "dmart.plugin.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("dynamicFeature") {
            id = "dmart.plugin.dynamic-feature"
            implementationClass = "DynamicFeatureConventionPlugin"
            version = "unspecified"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
dependencies {
    compileOnly(libs.gradle.plugin.agp)
    compileOnly(libs.gradle.plugin.kotlin)
    compileOnly(libs.gradle.plugin.ksp)
    compileOnly(libs.compose.compiler.extension)
}