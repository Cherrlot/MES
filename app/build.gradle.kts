plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.zhizhunbao.mes"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.zhizhunbao.mes"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        android.buildFeatures.buildConfig = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        //aRouter配置
        javaCompileOptions {
            annotationProcessorOptions {
                val map: HashMap<String, String> = HashMap()
                map["AROUTER_MODULE_NAME"] = project.name
                arguments(map)
            }
        }

        vectorDrawables {
            useSupportLibrary = true
        }

        // 开启 Dex 分包
        multiDexEnabled = true

        manifestPlaceholders["JPUSH_PKGNAME"] = "com.zhizhunbao.mes"
        manifestPlaceholders["JPUSH_APPKEY"] = libs.versions.jpush.appkey.get()//值来自开发者平台取得的AppKey
        manifestPlaceholders["JPUSH_CHANNEL"] = "default_developer"
        //xiaomi_config_start
        manifestPlaceholders["XIAOMI_APPID"] = "MI-小米的APPID"
        manifestPlaceholders["XIAOMI_APPKEY"] = "MI-小米的APPKEY"
        //xiaomi_config_end
        //oppo_config_start
        manifestPlaceholders["OPPO_APPKEY"] = "OP-oppo的APPKEY"
        manifestPlaceholders["OPPO_APPID"] = "OP-oppo的APPID"
        manifestPlaceholders["OPPO_APPSECRET"] = "OP-oppo的APPSECRET"
        //oppo_config_end
        //vivo_config_start
        manifestPlaceholders["VIVO_APPKEY"] = "vivo的APPKEY"
        manifestPlaceholders["VIVO_APPID"] = "vivo的APPID"
        //vivo_config_end
        //meizu_config_start
        manifestPlaceholders["MEIZU_APPKEY"] = "vivo的APPKEY"
        manifestPlaceholders["MEIZU_APPID"] = "vivo的APPID"
        //meizu_config_end
        //honor_config_start
        manifestPlaceholders["HONOR_APPID"] = "honor的APPID"
        //honor_config_end

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
    applicationVariants.all {
        outputs
            // default type don't have outputFileName field
            .map { it as com.android.build.gradle.internal.api.ApkVariantOutputImpl }
            .all { output ->
                /** 0为开发环境  1为测试环境  2为线上环境 3为打印日志的线上环境**/
                val netType =
                    if (libs.versions.netControl.get().toInt() == 0) {
                        "dev"
                    } else if (libs.versions.netControl.get().toInt() == 1) {
                        "test"
                    } else if (libs.versions.netControl.get().toInt() == 2) {
                        "realse"
                    } else if (libs.versions.netControl.get().toInt() == 3) {
                        "realse_log"
                    } else {
                        ""
                    }
                val versionName = libs.versions.versionName.get()
                val versionCode = libs.versions.versionCode.get()
                output.outputFileName = "EMS_${netType}_${versionName}_${versionCode}.apk"
                false
            }
    }
}

dependencies {
    implementation(project(":lib-common"))
    implementation(project(":module-login"))
    implementation(project(":module-mine"))
    implementation(project(":module-board"))
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)

    api(libs.arouter)
    kapt(libs.arouter.compiler)
}