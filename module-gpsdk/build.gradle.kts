plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.zhizhunbao.module.gpsdk"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //aRouter配置
        javaCompileOptions {
            annotationProcessorOptions {
                val map: HashMap<String, String> = HashMap()
                map["AROUTER_MODULE_NAME"] = project.name
                arguments(map)
            }
        }

    }

    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("libs/gprintersdkv1.jar"))
    implementation(project(":lib-common"))
    api(libs.arouter)
    kapt(libs.arouter.compiler)
}