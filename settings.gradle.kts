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
    ":core:nav",
    ":core:uikit",
    ":core:common",
)

include(
    ":core:database",
)

include(
    ":feature:today",
    ":feature:today-api"
)

include(":feature:assignments")
include(":feature:schedule")
include(":feature:settings")
include(":feature:event")
include(":feature:event-api")
include(":feature:assignments-api")
include(":feature:schedule-api")
include(":feature:settings-api")
