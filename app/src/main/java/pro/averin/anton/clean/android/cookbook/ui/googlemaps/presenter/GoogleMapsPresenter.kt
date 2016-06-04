package pro.averin.anton.clean.android.cookbook.ui.googlemaps.presenter

import pro.averin.anton.clean.android.cookbook.data.common.rx.Schedulers
import pro.averin.anton.clean.android.cookbook.data.flickr.FlickrRepo
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.presenter.BasePresenter
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapViewExtensionContract
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapViewExtensionDelegate
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapsScreenContract
import javax.inject.Inject

@ActivityScope
class GoogleMapsPresenter @Inject constructor(
        private val flickrRepo: FlickrRepo,
        private val schedulers: Schedulers
) : BasePresenter<GoogleMapsScreenContract>(), GoogleMapViewExtensionDelegate {

    lateinit var googleMapViewExtension: GoogleMapViewExtensionContract

    override fun onMapReady() {

    }

    override fun refreshWithCoordinates(latitude: Double, longitude: Double) {
        requestPhotos(latitude, longitude)
    }

    private fun requestPhotos(latitude: Double, longitude: Double) {
        val testRadius = 10
        flickrRepo.searchPhotos(latitude, longitude, testRadius)
                .subscribeOn(schedulers.io)
                .observeOn(schedulers.mainThread)
                .subscribe({
                               googleMapViewExtension.addPhotoMarkers(it)
                           }, {
                               it.printStackTrace()
                           }, {
                               googleMapViewExtension.navigateTo(latitude, longitude)
                           })
    }
}