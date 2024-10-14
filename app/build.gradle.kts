import com.cat.daily.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.daily.local"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.cicerone)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(project(path = ":core:router"))
    implementation(project(path = ":core:uikit"))
    implementation(project(path = ":core:assist"))

    implementation(project(path = ":feature:assignments"))
    implementation(project(path = ":feature:schedule"))
    implementation(project(path = ":feature:settings"))
    implementation(project(path = ":feature:today"))
    implementation(project(path = ":feature:event"))
}