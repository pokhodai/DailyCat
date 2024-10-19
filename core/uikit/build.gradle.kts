plugins {
    id("lib-config-plugin")
}

android {
    namespace = "com.cat.daily.local.core.uikit"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    implementation(project(path = ":core:recycler"))
    implementation(project(path = ":core:assist"))
}