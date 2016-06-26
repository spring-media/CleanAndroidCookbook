package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.any
import pro.averin.anton.clean.android.cookbook.kotlin.test.given
import pro.averin.anton.clean.android.cookbook.kotlin.test.mock
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import retrofit2.adapter.rxjava.HttpException

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        UIResolver::class,
        HttpException::class
)
class UIResolutionTest {

    @Mock lateinit var uiResolver: UIResolver

    @InjectMocks lateinit var classToTest: UIResolution

    @Test
    fun availableConnectivityHidesPersistentSnackBar() {
        // when
        classToTest.onConnectivityAvailable()

        // then
        verify(uiResolver).hidePersistentSnackBar()
    }

    @Test
    fun unavailableConnectivityShowsPersistentSnackBar() {
        // when
        classToTest.onConnectivityUnavailable()

        // then
        verify(uiResolver).showPersistentSnackBar(any())
    }

    @Test
    fun internalServerErrorShowsSnackBar() {
        // when
        classToTest.onInternalServerError()

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun serviceUnavailableShowsSnackbar() {
        // when
        classToTest.onServiceUnavailable()

        // then
        verify(uiResolver).showSnackBar(any())
    }


    @Test
    fun notFoundShowsSnackBar() {
        // when
        classToTest.onNotFound()

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun genericExceptionIsTracked() {
        //given
        val genericException = mock<Throwable>()

        // when
        classToTest.onGenericRxException(genericException)

        // then
        verify(genericException).printStackTrace()
    }

    @Test
    fun networkLocationErrorShowsSnackbar() {
        // when
        classToTest.onNetworkLocationError()

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun http500ExceptionHandledBySnackbarMessage() {
        // given
        val httpException = mock<HttpException>()
        given(httpException.code()).willReturn(500)

        // when
        classToTest.onHttpException(httpException)

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun http503ExceptionHandledBySnackbarMessage() {
        // given
        val httpException = mock<HttpException>()
        given(httpException.code()).willReturn(503)

        // when
        classToTest.onHttpException(httpException)

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun http404ExceptionHandledBySnackbarMessage() {
        // given
        val httpException = mock<HttpException>()
        given(httpException.code()).willReturn(404)

        // when
        classToTest.onHttpException(httpException)

        // then
        verify(uiResolver).showSnackBar(any())
    }

    @Test
    fun httpUnknownExceptionHandeledAsInternalServerError() {
        // given
        val httpException = mock<HttpException>()
        given(httpException.code()).willReturn(505)

        // when
        classToTest.onHttpException(httpException)

        // then
        verify(uiResolver).showSnackBar(any())
    }

}