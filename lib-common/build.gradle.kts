
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.org.jetbrains.kotlin.plugin.parcelize)
}

android {
    namespace = "com.zhizhunbao.lib.common"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        android.buildFeatures.buildConfig = true

        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        //aRouter配置
        javaCompileOptions {
            annotationProcessorOptions {
                val map: HashMap<String, String> = HashMap()
                map["AROUTER_MODULE_NAME"] = project.name
                arguments(map)
            }
        }

        ndk {
            //选择要添加的对应 cpu 类型的 .so 库。
            val set:  MutableSet<String> = HashSet()
            set.plus("armeabi")
            set.plus("armeabi-v7a")
            set.plus("arm64-v8a")
            abiFilters.addAll(set)
            // 还可以添加 "x86", "x86_64", "mips", "mips64"
        }

        // 日志tag
        buildConfigField("String", "PRINT_LOG_TAG", "\"Cherrlot\"")
        /** 0为本地环境  1为测试环境  2为线上环境 3为打印日志的线上环境 **/
        if (libs.versions.netControl.get().toInt() == 0) {
            buildConfigField("String", "BASE_URL", libs.versions.localApi.get())
            //日志打印
            buildConfigField("boolean", "PRINT_LOG", "true")
        } else if (libs.versions.netControl.get().toInt() == 1) {
            buildConfigField("String", "BASE_URL", libs.versions.testApi.get())
            //日志打印
            buildConfigField("boolean", "PRINT_LOG", "true")
        } else if (libs.versions.netControl.get().toInt() == 2) {
            buildConfigField("String", "BASE_URL", libs.versions.releaseApi.get())
            //日志打印
            buildConfigField("boolean", "PRINT_LOG", "false")
        }else if (libs.versions.netControl.get().toInt() == 3) {
            buildConfigField("String", "BASE_URL", libs.versions.releaseApi.get())
            //日志打印
            buildConfigField("boolean", "PRINT_LOG", "true")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":jiguang"))
    implementation(libs.activity.ktx)
    implementation(libs.fragment.ktx )
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    api(libs.zxing)
    api(libs.kotlin.stdlib)
    api(libs.pickerView)
    api(libs.roundedimageview)
    api(libs.updateapputils)
    api(libs.magicIndicator)
    api(libs.autosize)
    api(libs.multidex)
    api(libs.logger)
    api(libs.mmkv)
    api(libs.updateapputils)
    api(libs.startup)
    api(libs.liveEventBus)
    api(libs.kotlinx.coroutines.android)
    api(libs.easypermissions)
    api(libs.arouter)
    kapt(libs.arouter.compiler)

    api(libs.bundles.cameraxBundle)
    api(libs.bundles.pictureselectorBundle)
    api(libs.bundles.androidCommon)
    api(libs.bundles.retrofitBundle)
    api(libs.bundles.okhttpBundle)
    api(libs.bundles.koinBundle)
    api(libs.bundles.materialDialogBundle)
    api(libs.bundles.immersionbarBundle)
    api(libs.bundles.lifecycleBundle)
    api(libs.bundles.refreshBundle)
    api(libs.bundles.glideBundle)
}