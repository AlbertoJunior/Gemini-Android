plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.jetbrains)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.secrets.gradle)
    alias(libs.plugins.navigationSafeArgs)
    alias(libs.plugins.android.hilt)
    alias(libs.plugins.kotlin.kapt.jetbrains)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "albertojunior.setor0.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "albertojunior.setor0.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "local.defaults.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}

dependencies {
    implementation(project(":design"))
    implementation(project(":establishment"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.constraintlayout)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    implementation(libs.generativeai)

    implementation(libs.gson)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
//ksp {
//}