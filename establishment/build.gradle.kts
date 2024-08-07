plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.jetbrains)
    alias(libs.plugins.kotlin.kapt.jetbrains)
}

android {
    namespace = "albertojunior.setor0.app.establishment"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testOptions {
            targetSdk = 34
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
    implementation(libs.gson)

    implementation(libs.coreKtx)
    implementation(libs.constraintlayout)
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}