package com.example.main.di.module

import com.example.main.BuildConfig
import com.example.main.domain.network.RestApi
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestApiModule {

    @Provides
    @Singleton
    fun provideRestApi(okHttpClient: OkHttpClient, gson: Gson): RestApi =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(RestApi::class.java)
}