package com.example.main.data.persistance.db

import androidx.room.TypeConverter
import com.example.main.data.entities.venues.Venue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VenuesListConverter {

    private val venuesListToken = object: TypeToken<List<Venue>>() {}.type
    private val gson = Gson()

    @TypeConverter
    fun convertVenuesToString(venues: List<Venue>) = gson.toJson(venues)

    @TypeConverter
    fun convertStringToVenues(json: String) = gson.fromJson<List<Venue>>(json, venuesListToken)
}