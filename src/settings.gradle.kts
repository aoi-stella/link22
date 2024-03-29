pluginManagement {
    repositories {
        google()
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

rootProject.name = "link22"
include(":app")
include(":presentation")
include(":domain")
include(":data")
include(":util")

include(":app", "presentation")
include(":presentation", "util")
