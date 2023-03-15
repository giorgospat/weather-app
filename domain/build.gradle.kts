plugins {
    id(BuildPlugins.kotlin)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(Libraries.javaInject)
    implementation(Libraries.coroutines)

    testImplementation(TestLibraries.junit)
    testImplementation(TestLibraries.mockitoKotlin)
    testImplementation(TestLibraries.coroutines)
}