plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.jetbrains)
    alias(libs.plugins.kotlin.kapt.jetbrains)
    alias(libs.plugins.android.hilt)
}

android {
    namespace = "albertojunior.setor0.app.establishment"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
        testOptions {
            targetSdk = 36
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        debug {
            isMinifyEnabled = false
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
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":design"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.constraintlayout)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.gson)

    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}