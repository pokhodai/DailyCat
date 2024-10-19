package com.cat.daily.local.buildSrc.lib

import com.cat.daily.local.buildSrc.model.DepConfig
import com.cat.daily.local.buildSrc.model.MethodConfig
import org.gradle.api.artifacts.VersionCatalog

object HiltLib : BaseLib() {

    fun get(
        libs: VersionCatalog,
    ): DepConfig {
        val hiltLib = libs.findLibrary("hilt-android").get()
        val dependency = getDependency(
            module = "${hiltLib.get().module}",
            version = "${hiltLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependency
        )
    }

    fun getPlugin(
        libs: VersionCatalog,
    ): String {
        val hiltPlugin = libs.findPlugin("google-dagger-hilt").get()
        val hiltId = hiltPlugin.get().pluginId
        return hiltId
    }

    fun getCompiler(
        libs: VersionCatalog
    ): DepConfig {
        val hiltKsp = libs.findLibrary("hilt-android-compiler").get()
        val dependency = getDependency(
            module = "${hiltKsp.get().module}",
            version = "${hiltKsp.get().version}"
        )
        return DepConfig(
            method = MethodConfig.KSP,
            dependency = dependency
        )
    }
}