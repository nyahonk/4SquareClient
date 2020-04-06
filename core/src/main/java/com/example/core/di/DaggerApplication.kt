package com.example.core.di

import android.content.Context
import com.example.core.di.provider.CoreProvider

interface DaggerApplication {

    fun getApplicationContext(): Context

    fun getApplicationProvider(): CoreProvider
}