plugins {
    alias(libs.plugins.mahdipk.android.feature)
    alias(libs.plugins.mahdipk.android.compose)
}

android {
    namespace = "ir.romina.porkar.currencyconvertor"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:model"))
    implementation(project(":core:common"))


    testImplementation(libs.mockito)
    testImplementation(libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation("com.google.maps.android:maps-compose:4.3.3")
    implementation("com.google.maps.android:maps-compose-utils:4.3.3")
    implementation("com.google.maps.android:maps-compose-widgets:4.3.3")
    
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.test.manifest)
}