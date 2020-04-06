package com.example.main.domain.location

import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocationProvider @Inject constructor(
    context: Context
) {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fun getLastLocation(): Task<Location> =
        fusedLocationClient.lastLocation

}