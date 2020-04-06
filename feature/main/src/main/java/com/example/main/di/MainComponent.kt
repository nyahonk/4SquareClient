package com.example.main.di

import com.example.core.di.provider.CoreProvider
import com.example.main.di.module.DBModule
import com.example.main.di.module.MainModule
import com.example.main.di.module.RestApiModule
import com.example.main.ui.list.MainListFragment
import com.example.main.ui.map.MainMapFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    dependencies = [
        CoreProvider::class
    ],
    modules = [
        MainModule::class,
        DBModule::class,
        RestApiModule::class
    ]
)
interface MainComponent {

    fun inject(target: MainListFragment)
    fun inject(target: MainMapFragment)

    class Builder private constructor() {

        companion object {

            fun build(coreProvider: CoreProvider): MainComponent {
                return DaggerMainComponent.builder()
                    .coreProvider(coreProvider)
                    .build()
            }
        }
    }
}