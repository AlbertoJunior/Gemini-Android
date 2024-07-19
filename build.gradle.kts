// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.jetbrains) apply false

    alias(libs.plugins.kotlin.parcelize) apply false

    alias(libs.plugins.secrets.gradle) apply false

    alias(libs.plugins.android.hilt) apply false
    alias(libs.plugins.kotlin.kapt.jetbrains) apply false
    alias(libs.plugins.android.library) apply false
}