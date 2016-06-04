package pro.averin.anton.clean.android.cookbook.ui.googlemaps.view

import android.os.Bundle
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.data.flickr.model.Photo
import pro.averin.anton.clean.android.cookbook.kotlin.test.*
import pro.averin.anton.clean.android.cookbook.ui.common.map.MapUtils

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        MapUtils::class,
        GoogleMap::class,
        MarkerOptions::class,
        CameraUpdate::class,
        MapView::class
)
class GoogleMapViewExtensionTest {

    @Mock private lateinit var mapUtils: MapUtils
    @Mock private lateinit var eventsDelegate: GoogleMapViewExtensionDelegate
    @Mock private lateinit var map: MapView
    @Mock private lateinit var googleMap: GoogleMap

    @Mock private lateinit var markerOptions: MarkerOptions
    @Mock private lateinit var cameraUpdate: CameraUpdate

    @Captor lateinit var longClickCaptor: ArgumentCaptor<GoogleMap.OnMapLongClickListener>

    val photoList = listOf(Photo(), Photo())
    val lat = 1.0
    val lon = 2.0

    @InjectMocks lateinit var classToTest: GoogleMapViewExtension

    @Before
    fun setup() {
        classToTest.eventsDelegate = eventsDelegate
        classToTest.setViews(map)
    }

    @Test
    fun requestAsyncMapOnCreate() {
        // when
        classToTest.onCreate()

        // then
        verify(map).getMapAsync(classToTest)
    }

    @Test
    fun doesNothingWhenMapNotReady() {
        // when
        classToTest.onMapReady(null)

        // then
        verify(eventsDelegate, never()).onMapReady()
    }

    @Test
    fun notifiesDelegateOnMapReady() {
        // when
        classToTest.onMapReady(googleMap)

        // then
        verify(eventsDelegate).onMapReady()
    }

    @Test
    fun listensLongClickOnMap() {
        // when
        classToTest.onMapReady(googleMap)

        // then
        verify(googleMap).setOnMapLongClickListener(any())
    }

    @Test
    fun refreshesWithCoordinatesOnLongClick() {
        // given
        val latLon = LatLng(lat, lon)
        classToTest.onMapReady(googleMap)
        verify(googleMap).setOnMapLongClickListener(longClickCaptor.capture())

        // when
        longClickCaptor.value.onMapLongClick(latLon)

        // then
        verify(eventsDelegate).refreshWithCoordinates(lat, lon)
    }

    @Test
    fun addsPhotoMarkers() {
        // given
        classToTest.onMapReady(googleMap)
        given(mapUtils.buildMarkerOptions(any())).willReturn(markerOptions)

        // when
        classToTest.addPhotoMarkers(photoList)

        // then
        verify(googleMap, times(photoList.size)).addMarker(markerOptions)
    }

    @Test
    fun doesntAddMarkersWhenNoMap() {
        // given
        classToTest.onMapReady(null)

        // when
        classToTest.addPhotoMarkers(photoList)

        // then
        verify(googleMap, never()).addMarker(any())
    }

    @Test
    fun navigatesToCoordinates() {
        // given
        classToTest.onMapReady(googleMap)
        given(mapUtils.buildCameraUpdate(lat, lon)).willReturn(cameraUpdate)

        // when
        classToTest.navigateTo(lat, lon)

        // then
        verify(googleMap).animateCamera(cameraUpdate)
    }

    @Test
    fun doesnNavigateWhenNoMap() {
        // given
        classToTest.onMapReady(null)

        // when
        classToTest.navigateTo(lat, lon)

        // then
        verify(googleMap, never()).animateCamera(any())
    }


    @Test
    fun passesOnCreateToMap() {
        // given
        val bundle = Bundle()

        // when
        classToTest.onCreate(bundle)

        // then
        verify(map).onCreate(bundle)
    }

    @Test
    fun passesOnPauseToMap() {
        // when
        classToTest.onPause()

        // then
        verify(map).onPause()
    }

    @Test
    fun passesOnResumeToMap() {
        // when
        classToTest.onResume()

        // then
        verify(map).onResume()
    }

    @Test
    fun passesOnDestroyToMap() {
        // when
        classToTest.onDestroy()

        // then
        verify(map).onDestroy()
    }

}