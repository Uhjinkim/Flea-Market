plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "convention.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "convention.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("compose") {
            id = "convention.compose"
            implementationClass = "ComposeConventionPlugin"
        }
        register("feature") {
            id = "convention.feature"
            implementationClass = "FeatureConventionPlugin"
        }
        register("hilt") {
            id = "convention.hilt"
            implementationClass = "HiltConventionPlugin"
            version = "unspecified"
        }
        register("dynamicFeature") {
            id = "convention.dynamic-feature"
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
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}