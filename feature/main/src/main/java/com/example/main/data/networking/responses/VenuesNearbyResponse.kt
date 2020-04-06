package com.example.main.data.networking.responses


import com.example.main.data.entities.venues.Meta
import com.example.main.data.entities.venues.VenuesBody
import com.google.gson.annotations.SerializedName

data class VenuesNearbyResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("response")
    val response: VenuesBody
)