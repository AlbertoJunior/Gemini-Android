// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinJetbrains) apply false

    alias(libs.plugins.kotlinKaptJetbrains) apply false
    alias(libs.plugins.kotlinParcelize) apply false

    alias(libs.plugins.androidHilt) apply false
    alias(libs.plugins.secretsGradle) apply false

    alias(libs.plugins.secretsGradle)
}