package com.example.a4sclient.di

import com.example.a4sclient.App
import com.example.core.di.component.CoreComponent
import com.example.core.di.provider.CoreProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CoreProvider::class
    ]
)
interface AppComponent : CoreProvider {

    class Builder private constructor() {

        companion object {

            fun build(application: App): AppComponent {

                val coreProvider = CoreComponent.Builder.build(application)

                return DaggerAppComponent.builder()
                    .coreProvider(coreProvider)
                    .build()
            }
        }
    }
}