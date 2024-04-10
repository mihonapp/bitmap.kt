plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.spotless)
    `maven-publish`
}

kotlin {
    jvmToolchain(8)
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-Xexpect-actual-classes"
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }

        }


    }
    jvm {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-Xexpect-actual-classes"
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    // iosX64()
    // iosArm64()
    // iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.io)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "dev.mihon.bitmap"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }

    publishing {
        multipleVariants {
            allVariants()
            withSourcesJar()
        }
    }
}


spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/build/**/*.kt")
        ktlint(libs.ktlint.cli.get().version)
            .editorConfigOverride(
                mapOf(
                    "ktlint_standard_discouraged-comment-location" to "disabled",
                ),
            )
        trimTrailingWhitespace()
        endWithNewline()
    }
    format("misc") {
        target("**/.gitignore", "**/*.xml")
        targetExclude("**/build/**/*.xml")
        trimTrailingWhitespace()
        endWithNewline()
    }
}
