plugins {
    alias(libs.plugins.mahdipk.android.library)
    alias(libs.plugins.mahdipk.android.compose)
}

android {
    namespace = "pk.mahdi.core.ui.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}