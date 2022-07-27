import org.jetbrains.kotlin.util.capitalizeDecapitalize.capitalizeAsciiOnly

plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    kotlin(Plugins.kapt)
    id(Plugins.hilt)
    id(Plugins.kotlinParcelize)
    kotlin(Plugins.serialization).version(Versions.kotlin)
    id(Plugins.googleService)
    id(Plugins.crashlytics)

    id(Plugins.androidGitVersion).version(Versions.androidGitVersion)
    id(Plugins.checkDependencyUpdates).version(Versions.checkDependencyUpdates)
}

androidGitVersion {
    format = "%tag%%--branch%%--commit%"
}

android {
    compileSdk = Versions.compileSdk

    val generatedVersionCode = androidGitVersion.code()
    val generatedVersionName = androidGitVersion.name()
    val versionCodeValue = if (generatedVersionCode != 0) generatedVersionCode else 1

    setupOutputNames()

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

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf("room.schemaLocation" to "$projectDir/room_schemas")
            }
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
        appBuildTypes.forEach { buildType ->
            maybeCreate(buildType.name).apply {
                isDebuggable = buildType.isDebuggable
                isMinifyEnabled = buildType.isMinifyEnabled
                applicationIdSuffix = buildType.applicationIdSuffix
                versionNameSuffix = buildType.versionNameSuffix

                buildType.resValues.forEach {
                    resValue(it.type, it.name, it.value)
                }
                buildType.buildConfigValues.forEach {
                    buildConfigField(it.type, it.name, it.value)
                }
                buildType.manifestPlaceholders.forEach { (key, value) ->
                    manifestPlaceholders[key] = value
                }
            }
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
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"

    // Compose metrics
    // ./gradlew assembleRelease -P.enableComposeCompilerReports=true --rerun-tasks
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                "${project.buildDir.absolutePath}/compose_metrics"
    )
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                "${project.buildDir.absolutePath}/compose_metrics"
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

    implementation(Dependencies.paging3)
    implementation(Dependencies.paging3Compose)

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
    androidTestImplementation(Dependencies.truth)

    testImplementation(Dependencies.mockk)
    androidTestImplementation(Dependencies.mockk)
}

fun com.android.build.gradle.internal.dsl.BaseAppModuleExtension.setupOutputNames() {
    applicationVariants.all {
        outputs.all {
            val outputName = "podcasts-$versionName"

            // Rename .apk
            val outputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            outputImpl.outputFileName = "$outputName.apk"

            // Rename .aab
            // Get final bundle task name for this variant
            val bundleFinalizeTaskName = StringBuilder("sign").run {
                // Add each flavor dimension for this variant here
                productFlavors.forEach {
                    append(it.name.capitalizeAsciiOnly())
                }
                // Add build type of this variant
                append(buildType.name.capitalizeAsciiOnly())
                append("Bundle")
                toString()
            }
            tasks.named(
                bundleFinalizeTaskName,
                com.android.build.gradle.internal.tasks.FinalizeBundleTask::class.java
            ) {
                val file = finalBundleFile.asFile.get()
                val finalFile = File(file.parentFile, "$outputName.aab")
                finalBundleFile.set(finalFile)
            }
        }
    }
}
