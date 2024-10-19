package com.cat.daily.local.buildSrc.lib

import com.cat.daily.local.buildSrc.app.AppConfig
import com.cat.daily.local.buildSrc.model.DepConfig
import com.cat.daily.local.buildSrc.model.MethodConfig
import org.gradle.api.artifacts.VersionCatalog

object MaterialLib: BaseLib() {

    fun getMaterial(
        libs: VersionCatalog,
    ): DepConfig {
        val materialLib = libs.findLibrary("material").get()
        val dependencyMaterial = getDependency(
            module = "${materialLib.get().group}",
            name = materialLib.get().name,
            version = "${materialLib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependencyMaterial
        )
    }
}