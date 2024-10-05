plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.room)
    alias(libs.plugins.mahdipk.android.hilt)
}

android {
    namespace = "pk.mahdi.bookaven.database"
}

dependencies {
    implementation(project(":core:model"))
}