import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.local.core.recycler.adapter"
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.androidx.recyclerview)
}