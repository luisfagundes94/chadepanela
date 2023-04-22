import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link
import java.io.FileNotFoundException
import java.util.*

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kotlin.serialization)
}

group = "org.luisjulliana.bridalshower"
version = "1.0-SNAPSHOT"

setUpBuildConfigProperties()

kobweb {
    app {
        index {
            head.add {
                link(
                    rel = "stylesheet",
                    href = "https://fonts.googleapis.com/css2?family=Handlee&display=swap"
                )
                link(
                    rel = "stylesheet",
                    href = "https://fonts.googleapis.com/css2?family=Roboto&display=swap"
                )
            }
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("bridalshower", includeServer = true)
    jvmToolchain(11)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.datetime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)
                implementation(libs.koin.core)
             }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
                implementation(libs.kmongo)
                implementation(libs.kmongo.coroutine)
                implementation(libs.commons.email)
             }
        }
    }
}

fun setUpBuildConfigProperties() {
    val properties = Properties()
    val configFile = File("config.properties")
    if (configFile.exists()) {
        configFile.reader().use(properties::load)
    } else {
        throw FileNotFoundException("config.properties not found: $configFile")
    }

    val buildConfigPackage = "org.luisjulliana.bridalshower"
    val buildConfigContent = buildString {
        appendLine("package $buildConfigPackage")
        appendLine("")
        appendLine("object BuildConfig {")
        properties.forEach { key, value ->
            appendLine("    const val ${key.toString().toUpperCase()} = \"$value\"")
        }
        appendLine("}")
    }

    val buildConfigDir = File("$projectDir/src/jvmMain/kotlin/org/luisjulliana/bridalshower/envProperties")
    buildConfigDir.mkdirs()
    val buildConfigFile = File(buildConfigDir, "BuildConfig.kt")
    buildConfigFile.writeText(buildConfigContent)
}