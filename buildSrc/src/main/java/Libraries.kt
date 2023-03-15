object Libraries {

    /* https://developer.android.com/jetpack/androidx/releases/compose-ui */
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"

    /* https://developer.android.com/jetpack/androidx/releases/compose-material */
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeMaterial}"

    /* https://developer.android.com/jetpack/androidx/releases/lifecycle */
    const val runtimeLifecycleKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    const val composeLifecycle =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycle}"

    /* https://developer.android.com/training/dependency-injection/hilt-android */
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"

    /* https://mvnrepository.com/artifact/com.squareup.moshi/moshi-kotlin-codegen */
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    /* https://square.github.io/retrofit/ */
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"

    /* https://github.com/square/moshi/ */
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"

    /* https://github.com/square/okhttp */
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3Logging}"

    /*https://github.com/Kotlin/kotlinx.coroutines*/
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    /* https://mvnrepository.com/artifact/javax.inject/javax.inject */
    const val javaInject = "javax.inject:javax.inject:${Versions.javaInjectVersion}"

    /* https://developer.android.com/jetpack/compose/navigation */
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    /* https://google.github.io/accompanist/pager/ */
    const val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanist}"
    const val accompanistPagerIndicator =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanist}"
    const val accompanistPermissions =
        "com.google.accompanist:accompanist-permissions:${Versions.accompanist}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    /* https://developer.android.com/jetpack/androidx/releases/room */
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val gsmLocation = "com.google.android.gms:play-services-location:${Versions.gsmLocation}"

}