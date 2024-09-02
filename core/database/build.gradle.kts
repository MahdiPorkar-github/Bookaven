plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.room)
    alias(libs.plugins.mahdipk.android.hilt)
}

android {
    namespace = "ir.romina.porkar.database"
}

dependencies {
    implementation(project(":core:model"))
}