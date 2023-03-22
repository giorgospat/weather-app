buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(GradleLibraries.androidPlugin)
        classpath(GradleLibraries.hiltPlugin)
        classpath(GradleLibraries.kotlinPlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}