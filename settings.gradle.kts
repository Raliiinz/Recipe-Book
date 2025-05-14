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
    }
}

rootProject.name = "RecipeBook"
include(":app")
include(":core:base")
include(":core:base-feature")
include(":feature:authorization")
include(":core:data")
include(":core:domain")
include(":core:navigation")
include(":core:utils")
include(":core:network")
include(":feature:recipe-details")
include(":feature:registration")
include(":feature:search")
