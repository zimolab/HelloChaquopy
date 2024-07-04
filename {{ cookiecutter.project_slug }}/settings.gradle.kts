pluginManagement {
    repositories {
        {% if cookiecutter._extra_plugin_maven_repositories -%}
        {% for repo in cookiecutter._extra_plugin_maven_repositories -%}
        maven(url = "{{ repo }}")
        {% endfor -%}
        {% endif -%}
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
        {% if cookiecutter._extra_dependency_maven_repositories -%}
        {% for repo in cookiecutter._extra_dependency_maven_repositories -%}
        maven(url = "{{ repo }}")
        {% endfor -%}
        {% endif -%}
        google()
        mavenCentral()
    }
}

rootProject.name = "{{ cookiecutter.project_name }}"
include(":app")

