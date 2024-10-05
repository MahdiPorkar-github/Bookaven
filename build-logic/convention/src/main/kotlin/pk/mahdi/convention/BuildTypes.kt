package pk.mahdi.convention

import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }

        val apiKey = gradleLocalProperties(rootDir, rootProject.providers).getProperty("GOOGLE_API_KEY")
        extensions.configure<LibraryExtension> {
            buildTypes {
                debug {
                    configureDebugBuildType(apiKey)
                }
                release {
                    configureReleaseBuildType(commonExtension, apiKey)
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apiKey: String) {
    buildConfigField("String", "GOOGLE_API_KEY", apiKey)
}

private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    apiKey: String
) {
    buildConfigField("String", "GOOGLE_API_KEY", "\"$apiKey\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}