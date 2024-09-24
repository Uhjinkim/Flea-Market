import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.flea.feature)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.anotn.flea.favorite"

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    kotlin {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:data"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}