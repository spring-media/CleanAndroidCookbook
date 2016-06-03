package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo

interface GoogleMapViewExtensionContract {
    fun addPhotoMarkers(it: List<Photo>)

    fun navigateTo(latitude: Double, longitude: Double)

}
