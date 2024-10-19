plugins {
    id("lib-config-plugin")
    id("feature-config-plugin")
}

android {
    namespace = "com.cat.daily.local.feature.today"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(path = ":core:uikit"))
    implementation(project(path = ":core:router"))
    implementation(project(path = ":core:assist"))
    implementation(project(path = ":core:recycler"))
}