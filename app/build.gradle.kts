plugins {
    id(BuildPlugins.androidApplication)
    kotlin(BuildPlugins.android)
    kotlin(BuildPlugins.kapt)
    id(BuildPlugins.hilt)
}

android {

    namespace = "com.example.weatherapp"

    compileSdk = ConfigVersions.compileSdk

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = ConfigVersions.minSdk
        targetSdk = ConfigVersions.targetSdk
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
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
        jvmTarget = ConfigVersions.jvmTarget
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {

    implementation(project(":presentation"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))

    //core & ui
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.navigation)
    implementation(Libraries.composeLifecycle)

    //permissions
    implementation(Libraries.accompanistPermissions)

    //location
    implementation(Libraries.gsmLocation)

    //network
    implementation(Libraries.okhttpLogging)

    //serialization
    implementation(Libraries.moshi)

    //database
    implementation(Libraries.room)

    //dependency injection
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)

    //tests
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockitoKotlin)

}