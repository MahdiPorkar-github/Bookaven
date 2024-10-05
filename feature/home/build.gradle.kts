plugins {
    alias(libs.plugins.mahdipk.android.feature)
    alias(libs.plugins.mahdipk.android.compose)
}

android {
    namespace = "pk.mahdi.bookaven.feature.home"

    defaultConfig {

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:ui:navigation"))
    implementation(project(":core:ui:components"))


    testImplementation(libs.mockito)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}