package pro.averin.anton.clean.android.cookbook.data.flickr

import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrRepo @Inject constructor(
        private val flickrApi: FlickrAPIService
) {

    fun searchPhotos(lat: Double, lon: Double, radius: Int): Observable<List<Photo>> {
        return flickrApi.searchPhotos(lat, lon, radius)
                .map {
                    it.photo
                }
    }

}