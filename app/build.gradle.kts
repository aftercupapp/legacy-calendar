plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.aftercup.calendar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aftercup.calendar"
        minSdk = 7        // Android 2.1
        targetSdk = 10    // Android 2.3 (Stable legacy target)
        versionCode = 2
        versionName = "1.0.1"
    }

    signingConfigs {
        create("release") {
            storeFile = file(System.getProperty("user.home") + "/.android/debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
            enableV1Signing = true
            enableV2Signing = false
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
}