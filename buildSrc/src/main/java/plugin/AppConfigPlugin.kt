package com.cat.daily.local.buildSrc.plugin

import com.cat.daily.local.buildSrc.app.AppConfig
import com.cat.daily.local.buildSrc.lib.AndroidXLib
import com.cat.daily.local.buildSrc.lib.CiceroneLib
import com.cat.daily.local.buildSrc.lib.MaterialLib
import com.cat.daily.local.buildSrc.lib.ParcelizeLib
import org.gradle.api.Plugin
import org.gradle.api.Project
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
        target.plugins.apply("hilt-config-plugin")
        ParcelizeLib.configure(
            libs = libs,
            target = target
        )
        AppConfig.configureAndroid(target)

        val list = listOf(
            AndroidXLib.getAndroidXCoreKtx(libs),
            AndroidXLib.getAndroidXConstraintLayout(libs),
            AndroidXLib.getAndroidXAppCompat(libs),
            AndroidXLib.getAndroidXFragmentKtx(libs),
            AndroidXLib.getAndroidXActivityKtx(libs),
            MaterialLib.getMaterial(libs),
            CiceroneLib.get(libs),
        )

        AppConfig.setDependency(
            list = list,
            target = target,
        )
    }
}