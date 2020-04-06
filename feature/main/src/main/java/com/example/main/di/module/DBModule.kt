package com.example.main.di.module

import android.content.Context
import androidx.room.Room
import com.example.main.data.persistance.db.DataBase
import com.example.main.data.persistance.db.dao.VenuesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DataBase =
        Room.databaseBuilder(context.applicationContext,
                DataBase::class.java, "Sample.db")
            .build()

    @Provides
    @Singleton
    fun provideDatabaseDao(database: DataBase): VenuesDao =
        database.venuesDao()
}