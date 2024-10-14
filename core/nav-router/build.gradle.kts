import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
    id("kotlin-parcelize")
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.local.core.nav.router"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
}