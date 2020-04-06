package com.example.main.data.entities.venues


import com.google.gson.annotations.SerializedName

data class Venue(
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("hasPerk")
    val hasPerk: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("location")
    val venueLocation: VenueLocation,
    @SerializedName("name")
    val name: String,
    @SerializedName("referralId")
    val referralId: String,
    @SerializedName("venuePage")
    val venuePage: VenuePage
)