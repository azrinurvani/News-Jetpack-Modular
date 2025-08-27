pluginManagement {
    plugins{
        id("com.android.application") version "8.12.1"
        id("com.android.library") version "8.12.1"
        id("org.jetbrains.kotlin.android") version "2.2.10"
        id("com.google.devtools.ksp") version "2.2.0-2.0.2"
        id("com.google.dagger.hilt.android") version "2.57.1"
        id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    }
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

rootProject.name = "NewsInShort"
include(":app")
include(":utilities")
