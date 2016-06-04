package pro.averin.anton.clean.android.cookbook.ui.common.map

import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapUtils @Inject constructor() {

    companion object {
        val DEFAULT_ZOOM = 12f
    }

    fun buildMarkerOptions(photo: Photo): MarkerOptions {
        return MarkerOptions()
                .title(photo.title)
                .position(LatLng(photo.latitude, photo.longitude))
    }

    fun buildCameraUpdate(lat: Double, lon: Double): CameraUpdate {
        return CameraUpdateFactory.newLatLngZoom(LatLng(
                lat,
                lon), DEFAULT_ZOOM)
    }

}
