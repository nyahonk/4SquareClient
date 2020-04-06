package com.example.main.ui.map

import androidx.lifecycle.MutableLiveData
import com.example.core.presenter.BaseViewModel
import com.example.core.utils.ResourcesProvider
import com.example.main.R
import com.example.main.domain.interactor.VenuesInteractor
import com.example.main.domain.location.LocationProvider
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainMapViewModel @Inject constructor(
    private val venuesInteractor: VenuesInteractor,
    private val resourcesProvider: ResourcesProvider,
    private val locationProvider: LocationProvider
): BaseViewModel() {

    var lastSelectedMarker: Marker? = null

    val markerLiveData: MutableLiveData<List<MarkerOptions>> by lazy {
        MutableLiveData<List<MarkerOptions>>()
    }

    val errorLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val myLocationLiveData: MutableLiveData<LatLng> by lazy {
        MutableLiveData<LatLng>()
    }

    fun requestVenues() {
        locationProvider.getLastLocation().addOnSuccessListener {location ->

            myLocationLiveData.postValue(LatLng(location.latitude, location.longitude))

            venuesInteractor.getNearbyVenuesMarkers(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                    {
                        markerLiveData.postValue(it)
                    },
                    {
                        errorLiveData.postValue(it.message)
                    }).addToDisposables()
        }

    }

    fun markerSelected(marker: Marker) {
        lastSelectedMarker?.setIcon(resourcesProvider.getMarkerIcon(R.drawable.ic_map_pin_default))
        marker.setIcon(resourcesProvider.getMarkerIcon(R.drawable.ic_map_pin_selected))
        lastSelectedMarker = marker
    }
}