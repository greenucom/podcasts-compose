object Dependencies {

    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeMaterial3 = "androidx.compose.material3:material3:${Versions.composeMaterial3}"
    const val composeIconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val activityCompose = "androidx.activity:activity-ktx:${Versions.activityCompose}"

    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleViewModelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycle}"
    const val lifecycleViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"

    const val dataStorePreferences =
        "androidx.datastore:datastore-preferences:${Versions.dataStore}"

    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}"
    const val retrofitSerializationConverter =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.retrofitSerializationConverter}"

    const val serializationJson =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serializationJson}"

    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltComposeNavigation =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltComposeNavigation}"

    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"

    const val junit = "junit:junit:${Versions.junit}"
    const val androidJunit = "androidx.test.ext:junit:${Versions.androidJunit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"

}
