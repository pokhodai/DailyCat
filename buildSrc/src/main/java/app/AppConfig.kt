package com.cat.daily.local.buildSrc.app

import com.android.build.gradle.BaseExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import com.cat.daily.local.buildSrc.model.DepConfig
import org.gradle.api.JavaVersion
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object AppConfig {
    private const val COMPILE_SDK = 34
    private const val MIN_SDK = 24
    private const val TARGET_SDK = 34
    private const val VERSION_CODE = 1

    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0

    private const val VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"

    fun setDependency(
        list: List<DepConfig>,
        target: Project,
    ) {
        target.dependencies {
            list.forEach { config ->
                add(
                    configurationName = config.method.value,
                    dependencyNotation = config.dependency,
                )
            }
        }
    }

    fun configureAndroid(project: Project) {
        project.extensions.configure<BaseExtension> {
            compileSdkVersion(COMPILE_SDK )

            defaultConfig {
                minSdk = MIN_SDK
                targetSdk = TARGET_SDK
                versionCode = VERSION_CODE
                versionName = VERSION_NAME

                multiDexEnabled = true
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                }
            }

            project.tasks.withType(KotlinCompile::class.java).configureEach {
                kotlinOptions {
                    jvmTarget = "1.8"
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }
    }
}