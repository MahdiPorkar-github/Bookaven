plugins {
    alias(libs.plugins.mahdipk.jvm)
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlin.serialization.json)
}