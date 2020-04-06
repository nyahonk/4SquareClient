package com.example.a4sclient

import android.app.Application
import com.example.a4sclient.di.AppComponent
import com.example.core.di.DaggerApplication
import com.example.core.di.provider.CoreProvider

class App: Application(), DaggerApplication {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        buildDi()
    }

    override fun getApplicationProvider(): CoreProvider {
        return appComponent
    }

    private fun buildDi() {
        appComponent = AppComponent.Builder.build(this)
    }
}