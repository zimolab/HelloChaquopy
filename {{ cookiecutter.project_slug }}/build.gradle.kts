// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "{{ cookiecutter.agp_version }}" apply false
    id("org.jetbrains.kotlin.android") version "{{ cookiecutter.kgp_version }}" apply false

    /**
     * Specify the chaquo plugin version here
     *
     * See: https://chaquo.com/chaquopy/doc/current/android.html#android-plugin
     */
    id("com.chaquo.python") version "{{ cookiecutter.chaquopy_version }}" apply false
}