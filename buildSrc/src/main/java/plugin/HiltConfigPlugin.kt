package com.cat.daily.local.buildSrc.plugin

import com.cat.daily.local.buildSrc.app.AppConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import com.cat.daily.local.buildSrc.lib.HiltLib
import com.cat.daily.local.buildSrc.lib.KspLib

class HiltConfigPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        AppConfig.checkAppOrLib(target)

        val versionCatalogs = target.extensions.getByType<VersionCatalogsExtension>()
        val libs = versionCatalogs.named("libs")

        target.plugins.apply(HiltLib.getPlugin(libs))

        val isKsp = KspLib.isConfigure(
            libs = libs,
            target = target
        )

        val list = mutableListOf(
            HiltLib.get(libs)
        )

        if (isKsp) {
            list.add(HiltLib.getCompiler(libs))
        }

        AppConfig.setDependency(
            list = list,
            target = target,
        )
    }
}