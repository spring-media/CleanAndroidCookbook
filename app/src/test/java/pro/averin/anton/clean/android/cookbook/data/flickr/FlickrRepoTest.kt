package pro.averin.anton.clean.android.cookbook.data.flickr

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.data.flickr.model.FlickrPhotoFeed
import pro.averin.anton.clean.android.cookbook.data.flickr.model.FlickrResponse
import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import pro.averin.anton.clean.android.cookbook.kotlin.test.any
import pro.averin.anton.clean.android.cookbook.kotlin.test.given
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import rx.Observable
import rx.observers.TestSubscriber


@RunWith(PowerMockRunner::class)
@PrepareForTest()
class FlickrRepoTest {

    @Mock private lateinit var flickrApiSerivce: FlickrAPIService

    @InjectMocks lateinit var classToTest: FlickrRepo

    val photos = listOf(
            Photo(),
            Photo(),
            Photo()
    )

    val flickrResponse = FlickrResponse(
            photo = photos
    )

    val flickrPhotoFeed = FlickrPhotoFeed(flickrResponse)

    val lat = 1.0
    val lon = 2.0
    val radius = 10

    @Before
    fun setup() {
        given(flickrApiSerivce.searchPhotos(
                any(), any(), any(),
                any(), any(), any(), any(),
                any(), any(), any())
        ).willReturn(Observable.just(flickrPhotoFeed))
    }

    @Test
    fun searchesForPhotos() {
        // when
        classToTest.searchPhotos(lat, lon, radius).subscribe()

        // then
        verify(flickrApiSerivce).searchPhotos(lat, lon, radius)
    }

    @Test
    fun processesFlickrResponse() {
        // given
        val testSubscriber = TestSubscriber<List<Photo>>()

        // when
        classToTest.searchPhotos(lat, lon, radius).subscribe(testSubscriber)

        // then
        testSubscriber.assertValue(photos)
    }


}