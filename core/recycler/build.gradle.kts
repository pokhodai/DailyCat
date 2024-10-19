plugins {
    id("lib-config-plugin")
}

android {
    namespace = "com.cat.daily.local.core.recycler"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.recyclerview)
}