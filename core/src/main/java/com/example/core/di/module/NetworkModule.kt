package com.example.core.di.module

import com.example.core.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    private val requestTimeOutTime: Long = 20

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.readTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .writeTimeout(requestTimeOutTime, TimeUnit.SECONDS)
            .connectTimeout(requestTimeOutTime, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) builder.addNetworkInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()
}