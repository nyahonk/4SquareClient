package com.example.main.data.entities.venues


import com.google.gson.annotations.SerializedName

data class LabeledLatLng(
    @SerializedName("label")
    val label: String,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)