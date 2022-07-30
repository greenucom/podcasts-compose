package util

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

class PodcastIndexApiKeyHolder private constructor(
    val key: String,
    val secretKey: String,
) {

    companion object {

        fun init(project: Project): PodcastIndexApiKeyHolder {
            // Check local properties file first
            val localPropertiesFile =
                project.rootProject.file(LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE)
            if (localPropertiesFile.exists()) {
                val properties = Properties()
                properties.load(FileInputStream(localPropertiesFile))
                return PodcastIndexApiKeyHolder(
                    key = properties.getProperty(PROPERTIES_KEY, ""),
                    secretKey = properties.getProperty(PROPERTIES_SECRET_KEY, ""),
                )
            }

            // If there is no local properties file, check CI/CD env variables
            return PodcastIndexApiKeyHolder(
                key = System.getenv(CI_CD_KEY).orEmpty(),
                secretKey = System.getenv(CI_CD_SECRET_KEY).orEmpty(),
            )
        }

        private const val PROPERTIES_KEY = "key"
        private const val PROPERTIES_SECRET_KEY = "secretKey"

        private const val LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE = "podcast_index_api.properties"

        private const val CI_CD_KEY = "PODCAST_INDEX_API_KEY"
        private const val CI_CD_SECRET_KEY = "PODCAST_INDEX_API_SECRET_KEY"
    }
}
