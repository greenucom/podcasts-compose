val appBuildTypes = listOf(
    Debug,
    Release,
)

object Debug : BuildType("debug") {
    override val isDebuggable = true
    override val isMinifyEnabled = false

    override val resValues = listOf(
        ResValue("string", "app_name", "Debug")
    )
}

object Release : BuildType("release") {
    override val isDebuggable = false
    override val isMinifyEnabled = true

    override val resValues = listOf(
        ResValue("string", "app_name", "Podcasts")
    )
}

abstract class BuildType(val name: String) {
    abstract val isDebuggable: Boolean
    abstract val isMinifyEnabled: Boolean

    open val applicationIdSuffix: String = ".$name"
    open val versionNameSuffix: String = "--$name"

    open val resValues = listOf<ResValue>()
    open val buildConfigValues = listOf<BuildConfigField>()
}

class ResValue(val type: String, val name: String, val value: String)
class BuildConfigField(val type: String, val name: String, val value: String)
