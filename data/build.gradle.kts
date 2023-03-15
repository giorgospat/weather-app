import java.io.FileInputStream
import java.util.*

plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.android)
    kotlin(BuildPlugins.kapt)
}

android {

    namespace = "com.example.data"

    compileSdk = ConfigVersions.compileSdk

    defaultConfig {
        minSdk = ConfigVersions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    //fetch secrets
    val secretProperties = Properties()
    val secretsPropertiesFile = rootProject.file("secrets.properties")
    if (secretsPropertiesFile.exists()) {
        secretProperties.load(FileInputStream(secretsPropertiesFile))
    } else {
        secretProperties.setProperty("weather_api_key", System.getenv("WEATHER_API_KEY") ?: "")
        secretProperties.setProperty(
            "weather_api_key",
            System.getenv("WEATHER_API_KEY") ?: ""
        )
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")
            buildConfigField("String", "API_KEY", "\"${secretProperties.getProperty("weather_api_key")}\"")
        }
        getByName("release") {
            buildConfigField("String", "BASE_URL", "\"https://api.weatherapi.com/v1/\"")
            buildConfigField("String", "API_KEY", "\"${secretProperties.getProperty("weather_api_key")}\"")
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

}

dependencies {

    implementation(project(":domain"))
    implementation(project(":common"))

    //network
    implementation(Libraries.retrofit)
    implementation(Libraries.okhttpLogging)
    implementation(Libraries.coroutines)

    implementation(Libraries.javaInject)

    //database
    implementation(Libraries.room)
    implementation(Libraries.roomKtx)
    kapt(Libraries.roomCompiler)

    //serialization
    implementation(Libraries.moshi)
    implementation(Libraries.moshiConverter)
    kapt(Libraries.moshiCodegen)

    //test libraries
    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.coroutines)
}