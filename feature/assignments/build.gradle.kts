plugins {
    id("lib-config-plugin")
    id("feature-config-plugin")
}

android {
    namespace = "com.cat.daily.local.feature.assignments"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(path = ":core:assist"))
    implementation(project(path = ":core:recycler"))
    implementation(project(path = ":core:uikit"))
}