package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import retrofit2.adapter.rxjava.HttpException

@RunWith(PowerMockRunner::class)
@PrepareForTest(HttpException::class)
class ResolutionByCaseTest {

    @Mock lateinit var testStub: TestStub
    lateinit var classToTest: TestResolutionByCase

    @Mock lateinit var httpException: HttpException
    @Mock lateinit var genericRxException: Throwable

    val internalServerErrorMessage = "InternalServerError"
    val notFoundMessage = "NotFound"
    val serviceUnavailableMessage = "ServiceUnavailable"

    @Before
    fun setUp() {
        classToTest = TestResolutionByCase(testStub)
    }

    @Test
    fun httpExceptionWith500CallsOnInternalServerError() {
        // given
        BDDMockito.given(httpException.code()).willReturn(500)

        // when
        classToTest.onHttpException(httpException)

        // then
        BDDMockito.verify(testStub).callStub(internalServerErrorMessage)
    }

    @Test
    fun httpExceptionWith503CallsServiceUnavailable() {
        // given
        BDDMockito.given(httpException.code()).willReturn(503)

        // when
        classToTest.onHttpException(httpException)

        // then
        BDDMockito.verify(testStub).callStub(serviceUnavailableMessage)
    }

    @Test
    fun httpExceptionWith404CallsNotFound() {
        // given
        BDDMockito.given(httpException.code()).willReturn(404)

        // when
        classToTest.onHttpException(httpException)

        // then
        BDDMockito.verify(testStub).callStub(notFoundMessage)
    }

    @Test
    fun httpExceptionWithUnknownErrorCallInternalServerError() {
        // given
        BDDMockito.given(httpException.code()).willReturn(100500)

        // when
        classToTest.onHttpException(httpException)

        // then
        BDDMockito.verify(testStub).callStub(internalServerErrorMessage)
    }


    interface TestStub {
        fun callStub(message: String)
    }

    inner class TestResolutionByCase constructor(val testStub: TestStub) : ResolutionByCase() {
        override fun onNetworkLocationError() {
        }

        override fun onInternalServerError() {
            testStub.callStub(internalServerErrorMessage)
        }

        override fun onNotFound() {
            testStub.callStub(notFoundMessage)
        }

        override fun onServiceUnavailable() {
            testStub.callStub(serviceUnavailableMessage)
        }

        override fun onGenericRxException(t: Throwable) {
        }

        override fun onConnectivityAvailable() {
        }

        override fun onConnectivityUnavailable() {
        }
    }
}