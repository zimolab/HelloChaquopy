pluginManagement {
    repositories {
        {% if cookiecutter.maven_repos -%}
        {% for repo in cookiecutter.maven_repos['plugin'] -%}
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
        {% if cookiecutter.maven_repos -%}
        {% for repo in cookiecutter.maven_repos['dependency'] -%}
        maven(url = "{{ repo }}")
        {% endfor -%}
        {% endif -%}
        google()
        mavenCentral()
    }
}

rootProject.name = "{{ cookiecutter.project_name }}"
include(":app")

