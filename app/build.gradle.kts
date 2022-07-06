plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    kotlin(Plugins.serialization).version(Versions.kotlin)
    id(Plugins.googleService)
    id(Plugins.crashlytics)

    id(Plugins.androidGitVersion).version(Versions.androidGitVersion)
}

androidGitVersion {
    format = "%tag%"
}

android {
    compileSdk = Versions.compileSdk

    val generatedVersionCode = androidGitVersion.code()
    val generatedVersionName = androidGitVersion.name()
    val versionCodeValue = if (generatedVersionCode != 0) generatedVersionCode else 1

    defaultConfig {
        applicationId = "com.greencom.android.podcasts2"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = versionCodeValue
        versionName = generatedVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    val keystoreProperties = util.KeystoreHelper.createKeystoreProperties(project)

    signingConfigs {
        if (keystoreProperties != null) {
            create("defaultConfigs") {
                storeFile = file(keystoreProperties.getProperty(util.KeystoreHelper.STORE_FILE))
                storePassword = keystoreProperties.getProperty(util.KeystoreHelper.STORE_PASSWORD)
                keyAlias = keystoreProperties.getProperty(util.KeystoreHelper.KEY_ALIAS)
                keyPassword = keystoreProperties.getProperty(util.KeystoreHelper.KEY_PASSWORD)
            }
        }
    }

    buildTypes {

        getByName("debug") {
            resValue("string", "app_name", "Debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = " debug"
        }

        getByName("release") {
            resValue("string", "app_name", "Podcasts")
            isMinifyEnabled = true
        }

        all {
            if (keystoreProperties != null) {
                signingConfig = signingConfigs.getByName("defaultConfigs")
            }

            val podcastIndexApiProperties = util.PodcastIndexApiHelper
                .createPodcastIndexApiProperties(project)
            val podcastIndexApiKey = podcastIndexApiProperties
                .getProperty(util.PodcastIndexApiHelper.KEY, "")
            val podcastIndexApiSecretKey = podcastIndexApiProperties
                .getProperty(util.PodcastIndexApiHelper.SECRET_KEY, "")
            buildConfigField("String", "PODCAST_INDEX_API_KEY", "\"$podcastIndexApiKey\"")
            buildConfigField("String", "PODCAST_INDEX_API_SECRET_KEY", "\"$podcastIndexApiSecretKey\"")

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
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    // Rename outputs
    setProperty("archivesBaseName", "podcasts-$generatedVersionName")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"

    // Compose metrics
    // ./gradlew assembleRelease -P.enableComposeCompilerReports=true --rerun-tasks
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=${project.buildDir.absolutePath}/compose_metrics"
    )
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=${project.buildDir.absolutePath}/compose_metrics"
    )
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.composeMaterial3WindowSize)
    implementation(Dependencies.composeIconsExtended)
    implementation(Dependencies.composeUiToolingPreview)
    debugImplementation(Dependencies.composeUiTooling)
    androidTestImplementation(Dependencies.composeJunit)

    implementation(Dependencies.coroutines)

    implementation(Dependencies.core)
    implementation(Dependencies.coreSplashScreen)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.activity)

    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleViewModelCompose)

    implementation(Dependencies.navigationCompose)

    implementation(Dependencies.dataStorePreferences)

    implementation(Dependencies.room)
    kapt(Dependencies.roomCompiler)
    implementation(Dependencies.roomKtx)

    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitLoggingInterceptor)
    implementation(Dependencies.retrofitKotlinxSerializationConverter)

    implementation(Dependencies.kotlinxSerializationJson)
    implementation(Dependencies.kotlinxImmutableCollections)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltComposeNavigation)

    implementation(Dependencies.accompanistSystemUi)

    implementation(Dependencies.coil)
    implementation(Dependencies.timber)

    debugImplementation(Dependencies.leakCanary)

    implementation(platform(Dependencies.firebaseBom))
    implementation(Dependencies.analytics)
    implementation(Dependencies.crashlytics)

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidJunit)
    androidTestImplementation(Dependencies.androidEspresso)

    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.truthJava8)
    testImplementation(Dependencies.mockk)

}
