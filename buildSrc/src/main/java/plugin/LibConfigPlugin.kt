package com.cat.daily.local.buildSrc.plugin

import com.cat.daily.local.buildSrc.app.AppConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

class LibConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        val versionCatalogs = target.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")
        val jetbrainsKotlinAndroidPlugin = libs.findPlugin("jetbrains-kotlin-android").get()
        val jetbrainsKotlinAndroidId = jetbrainsKotlinAndroidPlugin.get().pluginId

        val androidLibraryPlugin = libs.findPlugin("android-library").get()
        val androidLibraryId = androidLibraryPlugin.get().pluginId

        target.plugins.apply(androidLibraryId)
        target.plugins.apply(jetbrainsKotlinAndroidId)

        AppConfig.configureAndroid(target)
    }
}