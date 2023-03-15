plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.android)
    kotlin(BuildPlugins.kapt)
}

android {

    namespace = "com.example.presentation"

    compileSdk = ConfigVersions.compileSdk

    defaultConfig {
        minSdk = ConfigVersions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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

}

dependencies {

    implementation(project(":domain"))

    //core & ui
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial)
    implementation(Libraries.runtimeLifecycleKtx)
    implementation(Libraries.composeLifecycle)
    implementation(Libraries.navigation)
    implementation(Libraries.accompanistPager)
    implementation(Libraries.accompanistPagerIndicator)
    implementation(Libraries.coil)

    //dependency injection
    implementation(Libraries.hilt)
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.hiltNavigation)

    //test libraries
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.coroutines)
    testImplementation(testFixtures(project(":common")))

}