package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import android.Manifest
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.tbruyelle.rxpermissions.RxPermissions
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegatingViewExtension
import javax.inject.Inject


interface GoogleMapViewExtensionDelegate : EventsDelegate {
    fun onMapReady()

    fun onMyLocationButtonClicked()
}

@ActivityScope
class GoogleMapViewExtension @Inject constructor(
        private val rxPermissions: RxPermissions
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
            setupMapLocationButton()
            eventsDelegate?.onMapReady()
        }
    }

    fun setupMapLocationButton() {
        googleMap?.let { map ->
            if (rxPermissions.isGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                map.isMyLocationEnabled = true
                map.setOnMyLocationButtonClickListener {
                    eventsDelegate?.onMyLocationButtonClicked()
                    true
                }
            }
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