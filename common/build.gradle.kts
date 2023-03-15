plugins {
    id(BuildPlugins.kotlin)
    id(BuildPlugins.javaTestFixtures)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    implementation(Libraries.javaInject)

    testFixturesImplementation(TestLibraries.junit)
    testFixturesImplementation(TestLibraries.mockitoKotlin)
    testFixturesImplementation(TestLibraries.coroutines)

}