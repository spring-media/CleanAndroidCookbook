package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import android.os.Bundle
import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.map.MapUtils
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegatingViewExtension
import javax.inject.Inject


interface GoogleMapViewExtensionDelegate : EventsDelegate {
    fun onMapReady()

    fun refreshWithCoordinates(latitude: Double, longitude: Double)
}

@ActivityScope
class GoogleMapViewExtension @Inject constructor(
        private val mapUtils: MapUtils
) : EventsDelegatingViewExtension<GoogleMapViewExtensionDelegate>, GoogleMapViewExtensionContract, OnMapReadyCallback {

    override var eventsDelegate: GoogleMapViewExtensionDelegate? = null

    private lateinit var map: MapView
    private var googleMap: GoogleMap? = null

    fun setViews(map: MapView) {
        this.map = map
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            map.onCreate(savedInstanceState)
        } catch (t: Throwable) {
            t.printStackTrace()
        }
        map.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        this.googleMap = googleMap

        googleMap?.let { map ->
            map.setOnMapLongClickListener {
                eventsDelegate?.refreshWithCoordinates(it.latitude, it.longitude)
            }

            eventsDelegate?.onMapReady()
        }
    }

    override fun addPhotoMarkers(photos: List<Photo>) {
        googleMap?.let { map ->
            photos.forEach {
                map.addMarker(mapUtils.buildMarkerOptions(it))
            }
        }
    }

    override fun navigateTo(latitude: Double, longitude: Double) {
        googleMap?.let {
            it.animateCamera(mapUtils.buildCameraUpdate(latitude, longitude))
        }
    }

    override fun onPause() {
        map.onPause()
    }

    override fun onResume() {
        map.onResume()
    }

    override fun onDestroy() {
        map.onDestroy()
    }

}