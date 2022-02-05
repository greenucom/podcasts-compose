import java.io.FileInputStream
import java.io.IOException
import java.util.*

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    kotlin(Plugins.serialization).version(Versions.kotlin)
    id(Plugins.googleService)

    id(Plugins.androidGitVersion).version(Versions.androidGitVersion)
}

androidGitVersion {
    format = "%tag%"
    hideBranches = listOf("demo")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
val keystoreExists = try {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
    true
} catch (e: IOException) {
    false
}
println("Keystore exists: $keystoreExists")

val apiPropertiesFile = rootProject.file("api.properties")
val apiProperties = Properties()
val apiKeysExist = try {
    apiProperties.load(FileInputStream(apiPropertiesFile))
    true
} catch (e: IOException) {
    false
}
println("Api keys exist: $apiKeysExist")

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

    applicationVariants.all {
        outputs.all {
            val variant = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            variant.outputFileName = "app.apk"
        }
    }

    signingConfigs {
        if (keystoreExists) {
            create("defaultConfigs") {
                storeFile = file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
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
            if (keystoreExists) {
                signingConfig = signingConfigs.getByName("defaultConfigs")
            }

            val apiKey: String
            val apiSecret: String
            if (apiKeysExist) {
                apiKey = apiProperties.getProperty("apiKey", "")
                apiSecret = apiProperties.getProperty("apiSecret", "")
            } else {
                apiKey = ""
                apiSecret = ""
            }
            buildConfigField("String", "apiKey", "\"$apiKey\"")
            buildConfigField("String", "apiSecret", "\"$apiSecret\"")

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
        kotlinCompilerExtensionVersion = "1.1.0-rc03"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

kotlin.sourceSets.all {
    languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeMaterial)
//    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeIconsExtended)
    implementation(Dependencies.composeUiToolingPreview)
    debugImplementation(Dependencies.composeUiTooling)
    androidTestImplementation(Dependencies.composeJunit)

    implementation(Dependencies.coroutines)

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.activityCompose)

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
    implementation(Dependencies.retrofitSerializationConverter)

    implementation(Dependencies.serializationJson)

    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltCompiler)
    implementation(Dependencies.hiltComposeNavigation)

    implementation(Dependencies.coil)
    implementation(Dependencies.timber)

    debugImplementation(Dependencies.leakCanary)

    implementation(platform(Dependencies.firebaseBom))

    testImplementation(Dependencies.junit)
    androidTestImplementation(Dependencies.androidJunit)
    androidTestImplementation(Dependencies.androidEspresso)

}
