package com.example.core.di.provider

import android.content.Context

interface ContextProvider {

    fun provideAppContext(): Context
}