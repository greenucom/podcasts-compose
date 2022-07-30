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
            val properties = Properties()
            val localPropertiesFile =
                project.rootProject.file(LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE)

            if (localPropertiesFile.exists()) {
                properties.load(FileInputStream(localPropertiesFile))
            } else {
                properties[KEY] = System.getenv(CI_CD_KEY).orEmpty()
                properties[SECRET_KEY] = System.getenv(CI_CD_SECRET_KEY).orEmpty()
            }

            return PodcastIndexApiKeyHolder(
                key = properties.getProperty(KEY, ""),
                secretKey = properties.getProperty(SECRET_KEY, ""),
            )
        }

        private const val LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE = "podcast_index_api.properties"

        private const val KEY = "key"
        private const val SECRET_KEY = "secretKey"

        private const val CI_CD_KEY = "PODCAST_INDEX_API_KEY"
        private const val CI_CD_SECRET_KEY = "PODCAST_INDEX_API_SECRET_KEY"
    }
}
