plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "ir.romina.porkar.network"
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.logger)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlin.serialization.json)

    implementation(project(":core:model"))
}