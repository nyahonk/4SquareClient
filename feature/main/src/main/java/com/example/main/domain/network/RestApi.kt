package com.example.main.domain.network

import com.example.main.data.networking.responses.VenuesNearbyResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("venues/search")
    fun getNearbyVenues(
        @Query("ll") ll : String,
        @Query("client_id") clientId: String,
        @Query("client_secret") clientSecret: String,
        @Query("v") date: String
    ): Single<VenuesNearbyResponse>
}