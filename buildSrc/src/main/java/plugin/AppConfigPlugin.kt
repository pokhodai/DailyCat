package com.cat.daily.local.buildSrc.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.cat.daily.local.buildSrc.app.AppConfig
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class AppConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val versionCatalogs = target.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")
        val androidAppPlugin = libs.findPlugin("android-application").get()
        val androidAppId = androidAppPlugin.get().pluginId
        val jetbrainsKotlinAndroidPlugin = libs.findPlugin("jetbrains-kotlin-android").get()
        val jetbrainsKotlinAndroidId = jetbrainsKotlinAndroidPlugin.get().pluginId

        target.plugins.apply(androidAppId)
        target.plugins.apply(jetbrainsKotlinAndroidId)

        AppConfig.configureAndroid(target)
    }
}