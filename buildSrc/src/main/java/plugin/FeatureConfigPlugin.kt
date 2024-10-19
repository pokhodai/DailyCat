package com.cat.daily.local.buildSrc.plugin

import com.cat.daily.local.buildSrc.app.AppConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import com.cat.daily.local.buildSrc.lib.AndroidXLib
import com.cat.daily.local.buildSrc.lib.MaterialLib

class FeatureConfigPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        AppConfig.checkAppOrLib(target)

        val versionCatalogs = target.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")

        target.plugins.apply("hilt-config-plugin")

        val list = listOf(
            AndroidXLib.getAndroidXCoreKtx(libs),
            AndroidXLib.getAndroidXConstraintLayout(libs),
            AndroidXLib.getAndroidXAppCompat(libs),
            AndroidXLib.getAndroidXFragmentKtx(libs),
            MaterialLib.getMaterial(libs)
        )

        AppConfig.setDependency(
            list = list,
            target = target,
        )
    }
}