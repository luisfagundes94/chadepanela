import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication
import kotlinx.html.link

@Suppress("DSL_SCOPE_VIOLATION") // https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.kotlin.serialization)
}

group = "org.luisjulliana.bridalshower"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            head.add {
                link(
                    rel = "stylesheet",
                    href = "https://fonts.googleapis.com/css2?family=Handlee&display=swap"
                )
            }
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("bridalshower", includeServer = true)
    jvmToolchain(11) // Kobweb server should use at least Java 11

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(libs.koin.core)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.web.core)
                implementation(libs.kobweb.core)
                implementation(libs.kobweb.silk.core)
                implementation(libs.kobweb.silk.icons.fa)
             }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kobweb.api)
                implementation(libs.kmongo)
                implementation(libs.kmongo.coroutine)
             }
        }
    }
}