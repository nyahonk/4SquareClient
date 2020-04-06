package com.example.core.utils

import android.content.Context
import android.location.Location
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeoUtils @Inject constructor(
    private val context: Context
) {

//    private val locationProvider = context.

    fun toLatLng(location: Location): LatLng {
        return LatLng(location.latitude, location.longitude)
    }

    fun toLatLng(latitude: Double, longitude: Double): LatLng {
        return LatLng(latitude, longitude)
    }

    fun getLatLngBounds(latLngList: MutableList<LatLng>): LatLngBounds? {
        if (latLngList.size == 0) {
            return null
        }
        val builder: LatLngBounds.Builder = LatLngBounds.Builder()
        if (latLngList.size == 1) {
            //Needed for correct zoom to single point
            val firstItem: LatLng = latLngList[0]
            latLngList.add(LatLng(firstItem.latitude + 0.005, firstItem.longitude))
        }
        latLngList.forEach {
            builder.include(it)
        }
        return builder.build()
    }
}