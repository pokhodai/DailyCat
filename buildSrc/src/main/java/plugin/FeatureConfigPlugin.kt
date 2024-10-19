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
        var isPlugin = false

        target.plugins.withId("com.android.application") {
            isPlugin = true
        }

        target.plugins.withId("com.android.library") {
            isPlugin = true
        }

        if (!isPlugin) {
            throw Exception("Добавьте com.android.library или com.android.application")
        }

        val versionCatalogs = target.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")

        configureFeature(
            libs = libs,
            target = target
        )

        target.plugins.apply("hilt-config-plugin")
    }

    private fun configureFeature(
        libs: VersionCatalog,
        target: Project
    ) {
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