import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "ir.romina.porkar.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "mahdipk.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("jvmLibrary") {
            id = "mahdipk.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("androidHilt") {
            id = "mahdipk.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "mahdipk.android.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeature") {
            id = "mahdipk.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidRoom") {
            id = "mahdipk.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
    }
}







