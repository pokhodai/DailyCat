package com.cat.school.local.buildSrc

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import com.cat.school.local.buildSrc.config.AppConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class AppConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.plugins.withId("com.android.application") {
            configureAndroid(target)
        }

        target.plugins.withId("com.android.library") {
            configureAndroid(target)
        }
    }

    private fun configureAndroid(project: Project) {
        project.extensions.configure<BaseExtension> {
            compileSdkVersion(AppConfig.COMPILE_SDK )

            defaultConfig {
                minSdk = AppConfig.MIN_SDK
                targetSdk = AppConfig.TARGET_SDK
                versionCode = AppConfig.VERSION_CODE
                versionName = AppConfig.VERSION_NAME

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