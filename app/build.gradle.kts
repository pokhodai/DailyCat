import com.cat.school.local.buildSrc.AppConfigPlugin

plugins {
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
}

apply<AppConfigPlugin>()

android {
    namespace = "com.cat.school.local"

    buildFeatures {
        viewBinding = true
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(project(path = ":core:router"))
    implementation(project(path = ":core:uikit"))

    implementation(project(path = ":presentation:today"))
    implementation(project(path = ":presentation:assignments"))
    implementation(project(path = ":presentation:schedule"))
    implementation(project(path = ":presentation:settings"))
}