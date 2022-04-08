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
    id("com.android.application") version(Versions.androidGradlePlugin) apply false
    id("com.android.library") version(Versions.androidGradlePlugin) apply false
    id("org.jetbrains.kotlin.android") version(Versions.kotlin) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
