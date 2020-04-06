package com.example.core.di.component

import com.example.core.di.DaggerApplication
import com.example.core.di.module.CoreModule
import com.example.core.di.module.NetworkModule
import com.example.core.di.provider.CoreProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CoreModule::class,
        NetworkModule::class
    ]
)
interface CoreComponent : CoreProvider {

    @Component.Builder
    interface ComponentBuilder {

        @BindsInstance
        fun application(daggerApplication: DaggerApplication): ComponentBuilder

        fun build(): CoreComponent
    }

    class Builder private constructor() {

        companion object {

            fun build(daggerApplication: DaggerApplication): CoreProvider {

                return DaggerCoreComponent.builder()
                    .application(daggerApplication)
                    .build()
            }
        }
    }
}