package com.example.main.ui.map

import android.location.Location
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.example.core.ui.BaseFragment
import com.example.core.utils.ResourcesProvider
import com.example.main.R
import com.example.main.di.MainComponent
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import javax.inject.Inject
import javax.inject.Provider


class MainMapFragment : BaseFragment(),
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener,
    OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var googleMap: GoogleMap

    override val layoutRes = R.layout.fragment_main_map

    override fun injectDependencies() {
        MainComponent.Builder
            .build(coreComponent)
            .inject(this)
    }

    @Inject
    lateinit var resProvider: ResourcesProvider
    @Inject
    lateinit var viewModelFactory: Provider<MainMapViewModel>

    private val viewModel: MainMapViewModel by lazy {
        viewModelFactory.get()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeMap()

        subscribeViews()
    }

    private fun subscribeViews() {
        val markersObserver = Observer<List<MarkerOptions>> {
            setMarkers(it)
        }
        val errorObserver = Observer<String> {
            showError(it)
        }
        val myLocationObserver = Observer<LatLng> {
            googleMap.animateCamera(
                CameraUpdateFactory
                    .newCameraPosition(CameraPosition.fromLatLngZoom(it, 18f))
            )
        }
        viewModel.apply {
            markerLiveData.observe(viewLifecycleOwner, markersObserver)
            errorLiveData.observe(viewLifecycleOwner, errorObserver)
            myLocationLiveData.observe(viewLifecycleOwner, myLocationObserver)
        }
    }

    private fun setMarkers(markers: List<MarkerOptions>) {

        markers.forEachIndexed { index, markerOptions ->
            val marker = googleMap.addMarker(markerOptions)
            marker.tag = index
        }
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        viewModel.markerSelected(marker)
        return false
    }

    override fun onMyLocationButtonClick(): Boolean {
        return false
    }

    override fun onMyLocationClick(location: Location) {
        showError("Current location:\n $location")
    }

    private fun initializeMap() {

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.isMyLocationEnabled = true
        viewModel.requestVenues()
        googleMap.setOnMarkerClickListener(this)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        clear()
    }

    private fun clear() {
        googleMap.clear()
    }
}