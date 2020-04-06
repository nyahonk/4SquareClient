package com.example.core.di.provider

import com.google.gson.Gson
import okhttp3.OkHttpClient

interface NetworkProvider {

    fun provideHttpClient(): OkHttpClient
    fun provideGson(): Gson
}