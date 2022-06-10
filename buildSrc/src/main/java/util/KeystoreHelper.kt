package util

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

object KeystoreHelper {

    fun createKeystoreProperties(project: Project): Properties? {
        // Check local
        val localKeystorePropertiesFile = project.rootProject.file(LOCAL_KEYSTORE_PROPERTIES_FILE)
        if (localKeystorePropertiesFile.exists()) {
            return Properties().apply {
                load(FileInputStream(localKeystorePropertiesFile))
            }
        }

        // Check CI/CD env variables
        val storeFile = System.getenv(CI_CD_STORE_FILE)
        val storePassword = System.getenv(CI_CD_STORE_PASSWORD)
        val keyAlias = System.getenv(CI_CD_KEY_ALIAS)
        val keyPassword = System.getenv(CI_CD_KEY_PASSWORD)
        return if (storeFile != null && storePassword != null && keyAlias != null && keyPassword != null) {
            Properties().apply {
                this[STORE_FILE] = storeFile
                this[STORE_PASSWORD] = storePassword
                this[KEY_ALIAS] = keyAlias
                this[KEY_PASSWORD] = keyPassword
            }
        } else {
            println("Warning: one or more keystore values retrieved from CI/CD env variables are null")
            null
        }
    }

    const val STORE_FILE = "storeFile"
    const val STORE_PASSWORD = "storePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASSWORD = "keyPassword"

    private const val CI_CD_STORE_FILE = "KEYSTORE_STORE_FILE"
    private const val CI_CD_STORE_PASSWORD = "KEYSTORE_STORE_PASSWORD"
    private const val CI_CD_KEY_ALIAS = "KEYSTORE_KEY_ALIAS"
    private const val CI_CD_KEY_PASSWORD = "KEYSTORE_KEY_PASSWORD"

    private const val LOCAL_KEYSTORE_PROPERTIES_FILE = "keystore.properties"

}
