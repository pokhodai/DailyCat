import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
    id(libs.plugins.kotlin.parcelize.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.local"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.cicerone)
    
    implementation(project(path = ":core:nav"))
    implementation(project(path = ":core:uikit"))
    implementation(project(path = ":core:common"))
    implementation(project(path = ":core:database"))

    implementation(project(path = ":feature:task"))
    implementation(project(path = ":feature:assignments"))
    implementation(project(path = ":feature:schedule"))
    implementation(project(path = ":feature:settings"))
    implementation(project(path = ":feature:event"))
}