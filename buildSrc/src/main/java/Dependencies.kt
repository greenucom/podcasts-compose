object Dependencies {

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeMaterial3WindowSize =
        "androidx.compose.material3:material3-window-size-class:${Versions.composeMaterial3}"
    const val composeIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val coreSplashScreen = "androidx.core:core-splashscreen:${Versions.coreSplashScreen}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val activity = "androidx.activity:activity-ktx:${Versions.activity}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"

    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"

    const val dataStorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}"
    const val retrofitKotlinxSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitKotlinxSerializationConverter}"

    const val kotlinxSerializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerializationJson}"
    const val kotlinxImmutableCollections =
        "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.kotlinxImmutableCollections}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltComposeNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    const val accompanistSystemUi =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"

    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

}
