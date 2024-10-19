plugins {
    id("app-config-plugin")
}

android {
    namespace = "com.cat.daily.local"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(path = ":core:router"))
    implementation(project(path = ":core:uikit"))
    implementation(project(path = ":core:assist"))

    implementation(project(path = ":feature:assignments"))
    implementation(project(path = ":feature:schedule"))
    implementation(project(path = ":feature:settings"))
    implementation(project(path = ":feature:today"))
    implementation(project(path = ":feature:event"))
}