import java.io.FileInputStream
import java.util.*

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
    format = "%tag%%-count%%-commit%%-dirty%"
}


val isBuildLocal = System.getenv("CI") == null


val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("keystore.properties")
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
} else {
    keystoreProperties["storeFile"] = System.getenv("KEYSTORE_FILE")
    keystoreProperties["storePassword"] = System.getenv("KEYSTORE_PASSWORD")
    keystoreProperties["keyAlias"] = System.getenv("KEYSTORE_SIGN_KEY_ALIAS")
    keystoreProperties["keyPassword"] = System.getenv("KEYSTORE_SIGN_KEY_PASSWORD")
}


val podcastIndexApiProperties = Properties()
val podcastIndexApiPropertiesFile = rootProject.file("podcast_index_api.properties")
if (podcastIndexApiPropertiesFile.exists()) {
    podcastIndexApiProperties.load(FileInputStream(podcastIndexApiPropertiesFile))
} else {
    podcastIndexApiProperties["key"] = System.getenv("PODCAST_INDEX_API_KEY")
    podcastIndexApiProperties["secretKey"] = System.getenv("PODCAST_INDEX_API_SECRET_KEY")
}


android {
    compileSdk = Versions.compileSdk

    val generatedVersionCode = androidGitVersion.code()
    val generatedVersionName = androidGitVersion.name()

    defaultConfig {
        applicationId = "com.greencom.android.podcasts2"
        minSdk = Versions.minSdk
        targetSdk = Versions.targetSdk
        versionCode = generatedVersionCode
        versionName = generatedVersionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("defaultConfigs") {
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {

        getByName("debug") {
            resValue("string", "app_name", "Podcasts debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = " debug"
        }

        create("qa") {
            resValue("string", "app_name", "Podcasts qa")
            applicationIdSuffix = ".qa"
            versionNameSuffix = " qa"
            isMinifyEnabled = true
        }

        create("demo") {
            resValue("string", "app_name", "Podcasts demo")
            applicationIdSuffix = ".demo"
            versionNameSuffix = " demo"
            isMinifyEnabled = true
        }

        getByName("release") {
            resValue("string", "app_name", "Podcasts")
            isMinifyEnabled = true
        }

        all {
            signingConfig = signingConfigs.getByName("defaultConfigs")

            val podcastIndexApiKey = podcastIndexApiProperties.getProperty("key", "")
            val podcastIndexApiSecretKey = podcastIndexApiProperties.getProperty("secretKey", "")
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
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

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

}
