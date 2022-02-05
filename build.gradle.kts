buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(BuildScriptPlugins.androidGradlePlugin)
        classpath(BuildScriptPlugins.kotlin)
        classpath(BuildScriptPlugins.hilt)
        classpath(BuildScriptPlugins.googleServices)
        classpath(BuildScriptPlugins.crashlytics)
    }

}

plugins {
    id("com.android.application") version("7.1.0") apply false
    id("com.android.library") version("7.1.0") apply false
    id("org.jetbrains.kotlin.android") version("1.6.10") apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
