package com.cat.daily.local.buildSrc.lib

import com.cat.daily.local.buildSrc.model.DepConfig
import com.cat.daily.local.buildSrc.model.MethodConfig
import org.gradle.api.artifacts.VersionCatalog

object AndroidXLib : BaseLib() {

    fun getAndroidXConstraintLayout(
        libs: VersionCatalog,
    ): DepConfig {
        val constraintLib = libs.findLibrary("androidx-constraintlayout").get()
        val dependencyConstraint = getDependency(
            module = "${constraintLib.get().group}",
            name = constraintLib.get().name,
            version = "${constraintLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependencyConstraint,
        )
    }

    fun getAndroidXCoreKtx(
        libs: VersionCatalog,
    ): DepConfig {
        val coreLib = libs.findLibrary("androidx-core-ktx").get()
        val dependencyCoreKts = getDependency(
            module = "${coreLib.get().group}",
            name = coreLib.get().name,
            version = "${coreLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependencyCoreKts,
        )
    }

    fun getAndroidXFragmentKtx(
        libs: VersionCatalog,
    ): DepConfig {
        val fragmentLib = libs.findLibrary("androidx-fragment-ktx").get()
        val dependencyFragment = getDependency(
            module = "${fragmentLib.get().module}",
            version = "${fragmentLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependencyFragment
        )
    }

    fun getAndroidXAppCompat(
        libs: VersionCatalog,
    ): DepConfig {
        val appCompatLib = libs.findLibrary("androidx-appcompat").get()
        val dependencyAppCompat = getDependency(
            module = "${appCompatLib.get().group}",
            name = appCompatLib.get().name,
            version = "${appCompatLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependencyAppCompat,
        )
    }
}