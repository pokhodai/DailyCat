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
    ":db",
    ":data",
    ":domain"
)

include(
    ":core:router",
    ":core:uikit",
    ":core:common"
)

include(":presentation:today")
include(":presentation:assignments")
include(":presentation:schedule")
include(":presentation:settings")