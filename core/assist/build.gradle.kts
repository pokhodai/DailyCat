plugins {
    id("lib-config-plugin")
    id("hilt-config-plugin")
}

android {
    namespace = "com.cat.daily.local.core.assist"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.android)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.viewbinding)
    implementation(libs.material)
}