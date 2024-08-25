plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.jetbrains.kotlin.android.get().pluginId)
    id(libs.plugins.google.devtools.ksp.get().pluginId)
    id(libs.plugins.google.dagger.hilt.get().pluginId)
}

android {
    namespace = "com.cat.school.core.repository"

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(project(path = ":core:db"))
}