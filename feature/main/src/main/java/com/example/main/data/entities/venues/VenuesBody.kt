package com.example.main.data.entities.venues


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "VenuesEntity")
data class VenuesBody(
    @PrimaryKey
    @SerializedName("confident")
    val confident: Boolean,
    @SerializedName("venues")
    val venues: List<Venue>
)