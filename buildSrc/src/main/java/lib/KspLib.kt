package com.cat.daily.local.buildSrc.lib

import com.cat.daily.local.buildSrc.app.AppConfig
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog

object KspLib {

    fun isConfigure(
        libs: VersionCatalog,
        target: Project
    ): Boolean {
        var isKsp = false

        target.plugins.withId("com.google.devtools.ksp") {
            isKsp = true
        }

        if (!isKsp) {
            val kspPlugin = libs.findPlugin("google-devtools-ksp").get()
            val kspId = kspPlugin.get().pluginId
            target.plugins.apply(kspId)
        }

        return isKsp
    }

}