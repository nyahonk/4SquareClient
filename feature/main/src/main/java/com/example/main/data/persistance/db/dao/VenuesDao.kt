package com.example.main.data.persistance.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.main.data.entities.venues.VenuesBody
import io.reactivex.Single

@Dao
interface VenuesDao {

    @Query("SELECT * FROM VenuesEntity")
    fun getVenuesBody(): Single<VenuesBody>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertVenuesBody(exchangeRatesEntity: VenuesBody): Single<Long>

    @Query("DELETE FROM VenuesEntity")
    fun deleteVenuesBody()
}