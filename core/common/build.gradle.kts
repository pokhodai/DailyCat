import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.core.common"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.hilt.android)
    implementation(libs.androidx.viewbinding)
    ksp(libs.hilt.android.compiler)
}