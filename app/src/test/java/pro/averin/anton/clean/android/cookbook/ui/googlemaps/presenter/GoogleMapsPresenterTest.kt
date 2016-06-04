package pro.averin.anton.clean.android.cookbook.ui.googlemaps.presenter

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.data.common.rx.MockSchedulers
import pro.averin.anton.clean.android.cookbook.data.flickr.FlickrRepo
import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import pro.averin.anton.clean.android.cookbook.kotlin.test.any
import pro.averin.anton.clean.android.cookbook.kotlin.test.eq
import pro.averin.anton.clean.android.cookbook.kotlin.test.given
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapViewExtensionContract
import rx.Observable

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        FlickrRepo::class
)
class GoogleMapsPresenterTest {

    @Mock private lateinit var flickrRepo: FlickrRepo
    @Mock private lateinit var googleMapViewExtension: GoogleMapViewExtensionContract
    val schedulers = MockSchedulers()

    val lat = 1.0
    val lon = 2.0
    val listPhoto = listOf(Photo(), Photo())

    private lateinit var classToTest: GoogleMapsPresenter

    @Before
    fun setup() {
        classToTest = GoogleMapsPresenter(
                flickrRepo,
                schedulers
        )
        classToTest.googleMapViewExtension = googleMapViewExtension

        given(flickrRepo.searchPhotos(any(), any(), any()))
                .willReturn(Observable.just(listPhoto))
    }

    @Test
    fun searchesPhotosOnRefreshWithCoordinates() {
        // when
        classToTest.refreshWithCoordinates(lat, lon)

        // then
        verify(flickrRepo).searchPhotos(eq(lat), eq(lon), any())
    }

    @Test
    fun addsMarkersForPhotos() {
        // when
        classToTest.refreshWithCoordinates(lat, lon)

        // then
        verify(googleMapViewExtension).addPhotoMarkers(listPhoto)
    }

    @Test
    fun navigatesToPositionOnComplete() {
        // when
        classToTest.refreshWithCoordinates(lat, lon)

        // then
        verify(googleMapViewExtension).navigateTo(lat, lon)
    }


}