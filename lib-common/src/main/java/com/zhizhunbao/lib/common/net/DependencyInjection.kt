package com.zhizhunbao.lib.common.net

import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import com.zhizhunbao.lib.common.BuildConfig
import com.zhizhunbao.lib.common.R
import com.zhizhunbao.lib.common.base.BaseResponse
import com.zhizhunbao.lib.common.base.BaseViewModel
import com.zhizhunbao.lib.common.ext.safe
import com.zhizhunbao.lib.common.log.AppLog
import com.zhizhunbao.lib.common.mmkv.AppLocalData
import com.zhizhunbao.lib.common.net.constant.API_PORT
import com.zhizhunbao.lib.common.net.constant.LOCAL_BASE
import com.zhizhunbao.lib.common.net.constant.StateType
import com.zhizhunbao.lib.common.net.constant.TIME_OUT
import com.zhizhunbao.lib.common.net.interceptor.TokenIntercept
import com.zhizhunbao.lib.common.net.log.InterceptorLogger
import com.zhizhunbao.lib.common.net.log.LoggerInterceptor
import com.zhizhunbao.lib.common.net.service.local.*
import com.zhizhunbao.lib.common.net.service.online.*
import com.zhizhunbao.lib.common.repository.*
import com.zhizhunbao.lib.common.tool.string
import com.zhizhunbao.lib.common.util.NetWorkUtil
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/** 网络请求 Module */
val netModule: Module = module {

    single {
        //缓存路径
        val logger = object : InterceptorLogger {
            override fun invoke(msg: String) {
                Logger.i(msg)
            }
        }
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(
                LoggerInterceptor(
                    logger,
                    if (BuildConfig.PRINT_LOG) LoggerInterceptor.LEVEL_BODY else LoggerInterceptor.LEVEL_NONE
                )
            )
            .addInterceptor(TokenIntercept())
            .build()
    }

    single<Retrofit>(named("webRetrofit")) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL + API_PORT + LOCAL_BASE)
            .addConverterFactory(ScalarsConverterFactory.create())// 通用转换器
            .addConverterFactory(GsonConverterFactory.create()) // 使用gson转换器
            .client(get())
            .build()
    }

    single<OnlineUserWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineUserWebService::class.java)
    }
    single<OnlineDishWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineDishWebService::class.java)
    }
    single<OnlineLimitWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineLimitWebService::class.java)
    }
    single<OnlineRetentionRecordWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineRetentionRecordWebService::class.java)
    }
    single<OnlineSettingWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineSettingWebService::class.java)
    }
    single<OnlinePurchaseWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlinePurchaseWebService::class.java)
    }
    single<OnlineNotifyWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineNotifyWebService::class.java)
    }
    single<OnlinePaperWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlinePaperWebService::class.java)
    }
    single<OnlineCheckWebService> {
        get<Retrofit>(named("webRetrofit")).create(OnlineCheckWebService::class.java)
    }
}

/** 本地菜品网络请求 Module */
val localDishNetModule: Module = module {

    single {
        //缓存路径
        val logger = object : InterceptorLogger {
            override fun invoke(msg: String) {
                Logger.i(msg)
            }
        }
        OkHttpClient.Builder()
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addNetworkInterceptor(
                LoggerInterceptor(
                    logger,
                    if (BuildConfig.PRINT_LOG) LoggerInterceptor.LEVEL_BODY else LoggerInterceptor.LEVEL_NONE
                )
            )
            .addInterceptor(TokenIntercept())
            .build()
    }
    single<Retrofit>(named("localDish")) {
        Retrofit.Builder()
            .baseUrl(AppLocalData.localIp + API_PORT + LOCAL_BASE)
            .addConverterFactory(ScalarsConverterFactory.create())// 通用转换器
            .addConverterFactory(GsonConverterFactory.create()) // 使用gson转换器
            .client(get())
            .build()
    }

    single<LocalDishWebService> {
        get<Retrofit>(named("localDish")).create(LocalDishWebService::class.java)
    }
    single<LocalLimitWebService> {
        get<Retrofit>(named("localDish")).create(LocalLimitWebService::class.java)
    }
    single<LocalReserveWebService> {
        get<Retrofit>(named("localDish")).create(LocalReserveWebService::class.java)
    }
    single<LocalRetentionWebService> {
        get<Retrofit>(named("localDish")).create(LocalRetentionWebService::class.java)
    }
    single<LocalRetentionRecordWebService> {
        get<Retrofit>(named("localDish")).create(LocalRetentionRecordWebService::class.java)
    }
}

/** 数据仓库 Module */
val repositoryModule: Module = module {
    factory { UserRepository(get()) }
    factory { ReserveRepository(get()) }
    factory { DishRepository(get(), get()) }
    factory { LimitRepository(get(), get()) }
    factory { RetentionRepository(get()) }
    factory { SettingRepository(get()) }
    factory { PurchaseRepository(get()) }
    factory { SelfNotifyRepository(get()) }
    factory { PaperRepository(get()) }
    factory { CheckRepository(get()) }
    factory { RetentionRecordRepository(get(), get()) }
}

/** 适配器 Module */
val adapterModule: Module = module {
}

fun <T> BaseViewModel.initiateRequest(
    block: suspend () -> BaseResponse<T>,
    success: ((T) -> Unit)? = null,
    failed: ((String?, StateType) -> Unit)? = null
): Job? {

    if (!NetWorkUtil.isInternetAvailable()) {
        failed?.invoke(R.string.no_net_error.string, StateType.NETWORK_ERROR)
        return null
    }
    return viewModelScope.launch {
        runCatching {
            block()
        }.onSuccess {
//            if (it.code == 200 || it.result) {
                // 成功
                success?.invoke(it.result)
//            } else {
//                // 失败
//                failed?.invoke(it.message, StateType.ERROR)
//                AppLog.e(it.message.safe())
//            }
        }.onFailure {
            // 异常
            NetExceptionHandle.handleException(it, failed)
        }
    }
}