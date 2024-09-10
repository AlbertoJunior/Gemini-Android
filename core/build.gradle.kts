plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.jetbrains)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "albertojunior.setor0.app.core"
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
}

dependencies {
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}