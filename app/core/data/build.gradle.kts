plugins {
    alias(libs.plugins.flea.library)
    alias(libs.plugins.flea.hilt)
    alias(libs.plugins.kotlin.serialization)
}
android {
    namespace = "com.anotn.flea.data"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.convertor)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.datastore.preferences)
}