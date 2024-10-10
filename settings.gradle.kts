pluginManagement {
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
        gradlePluginPortal()
    }
}

rootProject.name = "CatSchoolApp"
include(":app")

include(
    ":core:nav-router",
    ":core:uikit",
    ":core:common",
    ":core:recycler-adapter",
    ":core:database",
)

include(":feature:today")
include(":feature:assignments")
include(":feature:schedule")
include(":feature:settings")
include(":feature:event")
