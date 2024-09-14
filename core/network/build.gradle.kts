plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "pk.mahdi.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.logger)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)

    implementation(project(":core:model"))
}