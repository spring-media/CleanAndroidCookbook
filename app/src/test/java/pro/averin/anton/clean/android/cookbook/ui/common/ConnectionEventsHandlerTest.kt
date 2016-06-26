package pro.averin.anton.clean.android.cookbook.ui.common

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.data.common.connection.ConnectionChecker
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.GlobalBus
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.GlobalBusSubscriber
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityAvailableGlobalEvent
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityUnavailableGlobalEvent
import pro.averin.anton.clean.android.cookbook.kotlin.test.*
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        ConnectionChecker::class
)
class ConnectionEventsHandlerTest {

    @Mock private lateinit var connectionChecker: ConnectionChecker
    @Mock private lateinit var resolution: Resolution

    private val globalBus = GlobalBus()
    private val globalBusSubscriber = GlobalBusSubscriber(globalBus)

    private lateinit var classToTest: ConnectionEventsHandler

    @Before
    fun setup() {
        classToTest = ConnectionEventsHandler(
                globalBusSubscriber,
                connectionChecker
        )
    }

    @Test
    fun startSubscribes2Events() {
        // when
        classToTest.start(resolution)

        // then
        assertThat(globalBusSubscriber.subscriptions.size, isEqualTo(2))
    }

    @Test
    fun startResolvesConnectivityAvailableOnStart() {
        // given
        given(connectionChecker.isOnline()).willReturn(true)

        // when
        classToTest.start(resolution)

        // then
        verify(resolution).onConnectivityAvailable()
    }

    @Test
    fun startResolvesConnectivityUnavailableOnStart() {
        // given
        given(connectionChecker.isOnline()).willReturn(false)

        // when
        classToTest.start(resolution)

        // then
        verify(resolution).onConnectivityUnavailable()
    }

    @Test
    fun connectivityAvailableEventWhenStoppedDoesNothing() {
        // when
        globalBus.post(ConnectivityAvailableGlobalEvent())

        // then
        verify(resolution, never()).onConnectivityAvailable()
    }

    @Test
    fun connectivityUnavailableEventWhenStoppedDoesNothing() {
        // when
        globalBus.post(ConnectivityUnavailableGlobalEvent())

        // then
        verify(resolution, never()).onConnectivityUnavailable()
    }

    @Test
    fun connectivityAvailableEventWhenStartedResolved() {
        // given
        classToTest.start(resolution)

        // when
        globalBus.post(ConnectivityAvailableGlobalEvent())

        // then
        verify(resolution).onConnectivityAvailable()
    }

    @Test
    fun connectivityUnavailableEventWhenStartedResolved() {
        // given
        classToTest.start(resolution)

        // when
        reset(resolution)
        globalBus.post(ConnectivityUnavailableGlobalEvent())

        // then
        verify(resolution).onConnectivityUnavailable()
    }

    @Test
    fun stopUnsubscribesEvents() {
        // when
        classToTest.stop()

        // then
        assertThat(globalBusSubscriber.subscriptions.size, isEqualTo(0))
    }

}