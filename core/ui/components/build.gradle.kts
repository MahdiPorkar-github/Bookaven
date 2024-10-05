plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.compose)
}

android {
    namespace = "pk.mahdi.core.ui.components"
}

dependencies {
    implementation(libs.coil.compose)
    implementation(project(":core:designssystem"))
}