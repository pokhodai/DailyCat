plugins {
    id("lib-config-plugin")
    id("feature-config-plugin")
}

android {
    namespace = "com.cat.daily.local.feature.settings"

    buildFeatures {
        viewBinding = true
    }
}