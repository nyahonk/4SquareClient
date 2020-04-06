package com.example.main.data.persistance.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.main.data.entities.venues.VenuesBody
import com.example.main.data.persistance.db.dao.VenuesDao

@Database(entities = [VenuesBody::class], version = 1)
@TypeConverters(VenuesListConverter::class)
abstract class DataBase : RoomDatabase() {

    abstract fun venuesDao(): VenuesDao
}