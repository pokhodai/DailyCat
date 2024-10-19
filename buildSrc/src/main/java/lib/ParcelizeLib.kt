package com.cat.daily.local.buildSrc.lib

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog

object ParcelizeLib {

    fun configure(
        libs: VersionCatalog,
        target: Project
    ) {
        val plugin = libs.findPlugin("kotlin-parcelize").get()
        val id = plugin.get().pluginId
        target.plugins.apply(id)
    }
}