package com.cat.daily.local.buildSrc.lib

import com.cat.daily.local.buildSrc.lib.HiltLib.getDependency
import com.cat.daily.local.buildSrc.model.DepConfig
import com.cat.daily.local.buildSrc.model.MethodConfig
import org.gradle.api.artifacts.VersionCatalog

object CiceroneLib {

    fun get(
        libs: VersionCatalog
    ): DepConfig {
        val lib = libs.findLibrary("cicerone").get()
        val dependency = getDependency(
            module = "${lib.get().module}",
            version = "${lib.get().version}"
        )
        return DepConfig(
            method = MethodConfig.IMPL,
            dependency = dependency,
        )
    }
}