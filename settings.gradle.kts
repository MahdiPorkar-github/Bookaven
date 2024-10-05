gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Bookaven"
include(":app")
include(":core")
include(":core:network")
include(":core:model")
include(":core:data")
include(":core:designssystem")
include(":core:database")
include(":core:common")
include(":feature")
include(":feature:home")
include(":core:ui:navigation")
include(":feature:book_detail")
include(":feature:categories")
include(":feature:library")
include(":feature:settings")
include(":core:ui:components")
