package util

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.*

object PodcastIndexApiHelper {

    fun createPodcastIndexApiProperties(project: Project): Properties {
        val properties = Properties()
        val localPropertiesFile = project.rootProject.file(LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE)
        if (localPropertiesFile.exists()) {
            properties.load(FileInputStream(localPropertiesFile))
        } else {
            properties[KEY] = System.getenv(CI_CD_KEY).orEmpty()
            properties[SECRET_KEY] = System.getenv(CI_CD_SECRET_KEY).orEmpty()
        }
        return properties
    }

    const val KEY = "key"
    const val SECRET_KEY = "secretKey"

    private const val CI_CD_KEY = "PODCAST_INDEX_API_KEY"
    private const val CI_CD_SECRET_KEY = "PODCAST_INDEX_API_SECRET_KEY"

    private const val LOCAL_PODCAST_INDEX_API_PROPERTIES_FILE = "podcast_index_api.properties"

}
