package com.damai.mycasestudy.module

import com.damai.core.ApplicationDispatchersProvider
import com.damai.core.SchedulerProvider
import com.damai.data.service.HomeService
import com.damai.mycasestudy.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by damai.subimawanto on 7/5/2022.
 */
private const val TIMEOUT: Long = 60
private const val BASE_URL = "https://run.mocky.io"

val networkModule = module {
    factory<SchedulerProvider> { ApplicationDispatchersProvider() }

    single { GsonBuilder().create() }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder().apply{
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor(logging)
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory { get<Retrofit>().create(HomeService::class.java) }
}