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
    includeBuild("build-logic")
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/")}
    }
}


rootProject.name = "Flea Market"
include(":app")

include(":feature")
include(":feature:feed")
include(":feature:favorite")
include(":feature:chat")

include(":core")
include(":core:domain")
include(":core:designsystem")

include(":feature:more")
include(":feature:write")
include(":core:data")
include(":feature:search")
