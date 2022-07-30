package util

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

class SigningConfig private constructor(
    val name: String,
    val storeFile: String,
    val storePassword: String,
    val keyAlias: String,
    val keyPassword: String,
) {

    companion object {

        fun getDefault(project: Project): SigningConfig? {
            // Check local properties file first
            val localPropertiesFile = project.rootProject.file(LOCAL_KEYSTORE_PROPERTIES_FILE)
            if (localPropertiesFile.exists()) {
                val properties = Properties()
                properties.load(FileInputStream(localPropertiesFile))
                println("SigningConfig created from the local properties file")
                return SigningConfig(
                    name = SIGNING_CONFIG_NAME_DEFAULT,
                    storeFile = properties.getProperty(PROPERTIES_STORE_FILE, ""),
                    storePassword = properties.getProperty(PROPERTIES_STORE_PASSWORD, ""),
                    keyAlias = properties.getProperty(PROPERTIES_KEY_ALIAS, ""),
                    keyPassword = properties.getProperty(PROPERTIES_KEY_PASSWORD, ""),
                )
            }

            // If there is no local properties file, check CI/CD env variables
            val storeFile = System.getenv(CI_CD_STORE_FILE) ?: return null
            val storePassword = System.getenv(CI_CD_STORE_PASSWORD) ?: return null
            val keyAlias = System.getenv(CI_CD_KEY_ALIAS) ?: return null
            val keyPassword = System.getenv(CI_CD_KEY_PASSWORD) ?: return null
            println("SigningConfig created from CI/CD env variables")
            return SigningConfig(
                name = SIGNING_CONFIG_NAME_DEFAULT,
                storeFile = storeFile,
                storePassword = storePassword,
                keyAlias = keyAlias,
                keyPassword = keyPassword
            )
        }

        private const val SIGNING_CONFIG_NAME_DEFAULT = "defaultConfig"

        private const val PROPERTIES_STORE_FILE = "storeFile"
        private const val PROPERTIES_STORE_PASSWORD = "storePassword"
        private const val PROPERTIES_KEY_ALIAS = "keyAlias"
        private const val PROPERTIES_KEY_PASSWORD = "keyPassword"

        private const val LOCAL_KEYSTORE_PROPERTIES_FILE = "keystore.properties"

        private const val CI_CD_STORE_FILE = "KEYSTORE_STORE_FILE"
        private const val CI_CD_STORE_PASSWORD = "KEYSTORE_STORE_PASSWORD"
        private const val CI_CD_KEY_ALIAS = "KEYSTORE_KEY_ALIAS"
        private const val CI_CD_KEY_PASSWORD = "KEYSTORE_KEY_PASSWORD"
    }
}
