package com.example.main.ui.list

import androidx.lifecycle.MutableLiveData
import com.example.core.presenter.BaseViewModel
import com.example.main.data.entities.venues.Venue
import com.example.main.domain.interactor.VenuesInteractor
import com.example.main.domain.location.LocationProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainListViewModel @Inject constructor(
    private val venuesInteractor: VenuesInteractor,
    private val locationProvider: LocationProvider
) : BaseViewModel() {

    val venues: MutableLiveData<List<Venue>> by lazy {
        MutableLiveData<List<Venue>>()
    }

    val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    init {
        getVenues()
    }

    private fun getVenues() {
        locationProvider.getLastLocation().addOnSuccessListener {location ->
            venuesInteractor.getNearbyVenues(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        venues.postValue(it)
                    },
                    {
                        error.postValue(it.message)
                    }).addToDisposables()
        }
    }
}