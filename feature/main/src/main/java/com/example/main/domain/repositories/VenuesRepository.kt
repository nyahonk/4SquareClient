package com.example.main.domain.repositories

import com.example.core.utils.ResourcesProvider
import com.example.main.BuildConfig
import com.example.main.data.entities.venues.Venue
import com.example.main.data.persistance.db.DataBase
import com.example.main.domain.network.RestApi
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VenuesRepository @Inject constructor(
    private val localDb: DataBase,
    private val api: RestApi,
    private val resourcesProvider: ResourcesProvider
) {

    fun getNearbyVenues(ll: String): Single<List<Venue>> =
        api.getNearbyVenues(ll, BuildConfig.CLIENT_ID, BuildConfig.CLIENT_SECRET, resourcesProvider.getCurrentDateForRequest())
            .flatMap { response ->
                localDb.venuesDao()
                    .insertVenuesBody(response.response)
                    .map {
                    response.response.venues
                }
            }
            .onErrorResumeNext(
                localDb.venuesDao()
                    .getVenuesBody()
                    .map { it.venues }
            )


}