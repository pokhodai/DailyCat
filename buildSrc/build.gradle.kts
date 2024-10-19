plugins {
    `kotlin-dsl`
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

repositories {
    mavenCentral()
    google()

    gradlePlugin {
        plugins {
            register("app-config-plugin") {
                description = "AppConfigPlugin"
                id = "app-config-plugin"
                implementationClass = "com.cat.daily.local.buildSrc.plugin.AppConfigPlugin"
            }

            register("lib-config-plugin") {
                description = "LibConfigPlugin"
                id = "lib-config-plugin"
                implementationClass = "com.cat.daily.local.buildSrc.plugin.LibConfigPlugin"
            }

            register("feature-config-plugin") {
                description = "FeatureConfigPlugin"
                id = "feature-config-plugin"
                implementationClass = "com.cat.daily.local.buildSrc.plugin.FeatureConfigPlugin"
            }

            register("hilt-config-plugin") {
                description = "HiltConfigPlugin"
                id = "hilt-config-plugin"
                implementationClass = "com.cat.daily.local.buildSrc.plugin.HiltConfigPlugin"
            }
        }
    }
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.javapoet)
    implementation(gradleApi())
}