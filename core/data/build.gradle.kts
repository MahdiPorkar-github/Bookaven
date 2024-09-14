plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.hilt)
    alias(libs.plugins.mahdipk.android.room)
}

android {
    namespace = "ir.romina.porkar.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))

    implementation(libs.androidx.work)
    implementation(libs.hilt.ext.work)
}