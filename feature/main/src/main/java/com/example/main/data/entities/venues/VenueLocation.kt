package com.example.main.data.entities.venues


import com.google.gson.annotations.SerializedName

data class VenueLocation(
    @SerializedName("address")
    val address: String,
    @SerializedName("cc")
    val cc: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("crossStreet")
    val crossStreet: String,
    @SerializedName("distance")
    val distance: Int,
    @SerializedName("formattedAddress")
    val formattedAddress: List<String>,
    @SerializedName("labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLng>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("neighborhood")
    val neighborhood: String,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("state")
    val state: String
)