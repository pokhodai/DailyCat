import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.local.feature.task"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.fragment.ktx)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(project(path = ":core:uikit"))
    implementation(project(":core:nav"))
    implementation(project(":feature:task-api"))
}