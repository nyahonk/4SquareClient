package com.example.core.di.module

import android.content.Context
import com.example.core.utils.ResourcesProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoreModule {


    @Singleton
    @Provides
    fun provideApplicationContext(application: com.example.core.di.DaggerApplication): Context {
        return application.getApplicationContext()
    }

    @Singleton
    @Provides
    fun provideResourcesProvider(context: Context) = ResourcesProvider(context)

}