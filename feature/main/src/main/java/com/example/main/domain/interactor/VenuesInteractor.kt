package com.example.main.domain.interactor

import android.location.Location
import com.example.core.utils.ResourcesProvider
import com.example.main.R
import com.example.main.data.entities.venues.Venue
import com.example.main.domain.location.LocationProvider
import com.example.main.domain.repositories.VenuesRepository
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VenuesInteractor @Inject constructor(
    private val repository: VenuesRepository,
    private val resourcesProvider: ResourcesProvider
) {

    fun getNearbyVenues(location: Location?): Single<List<Venue>> =
        repository.getNearbyVenues(getLatLngString(location))


    fun getNearbyVenuesMarkers(location: Location?): Single<List<MarkerOptions>> =
        getNearbyVenues(location).map { convertVenuesToMarkers(it) }

    private fun convertVenuesToMarkers(venues: List<Venue>): List<MarkerOptions> {
        val markers = ArrayList<MarkerOptions>()

        venues.forEach {
            markers.add(
                MarkerOptions().title(it.name)
                    .position(LatLng(it.venueLocation.lat, it.venueLocation.lng))
                    .icon(resourcesProvider.getMarkerIcon(R.drawable.ic_map_pin_default))
            )
        }
        return markers
    }
    private fun getLatLngString(location: Location?): String {
        return if (location != null) "${location.latitude},${location.longitude}" else "40.7484,-73.9857"
    }
        //"40.7484,-73.9857"
}