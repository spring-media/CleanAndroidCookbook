package pro.averin.anton.clean.android.cookbook.data.common.resolution

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.UIResolver
import retrofit2.adapter.rxjava.HttpException

@RunWith(PowerMockRunner::class)
@PrepareForTest(HttpException::class, UIResolver::class)
class ResolvedSubscriberTest {

    @Mock lateinit var resolution: Resolution
    @Mock lateinit var obj: Throwable

    @Mock lateinit var httpException: HttpException
    @Mock lateinit var genericRxException: Throwable

    var onNextFunc: (Throwable) -> Unit = {
        it.printStackTrace()
    }

    var onCompletedFunc: () -> Unit = {

    }

    lateinit var classToTest: ResolvedSubscriber<Throwable>

    @Before
    fun setup() {
        classToTest = ResolvedSubscriber(resolution, onNextFunc, onCompletedFunc)
    }

    @Test
    fun onNextCallsOnNextFunc() {
        // when
        classToTest.onNext(obj)

        // then
        verify(obj).printStackTrace()
    }

    @Test
    fun onErrorCallsHttpResolutionOnHttpException() {
        // when
        classToTest.onError(httpException)

        // then
        verify(resolution).onHttpException(httpException)
    }

    @Test
    fun onErrorCallsGenericResolutionOnGenericRxException() {
        // when
        classToTest.onError(genericRxException)

        // then
        verify(resolution).onGenericRxException(genericRxException)
    }

}