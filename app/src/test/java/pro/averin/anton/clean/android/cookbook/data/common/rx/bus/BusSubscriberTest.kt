package pro.averin.anton.clean.android.cookbook.data.common.rx.bus

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.*
import rx.Observable
import rx.Subscription
import rx.functions.Action1
import rx.subscriptions.CompositeSubscription

@RunWith(PowerMockRunner::class)
@PrepareForTest(RxBus::class, CompositeSubscription::class, Observable::class)
class BusSubscriberTest {

    @Mock lateinit var subscription: Subscription
    @Mock lateinit var bus: RxBus
    @Mock lateinit var observable: Observable<Any>

    @Captor var subscriptionCaptor = ArgumentCaptor.forClass(Subscription::class.java)

    @InjectMocks lateinit var classToTest: BusSubscriber

    @Before
    fun setup() {
        classToTest.bus(bus)
    }

    @Test
    fun subscribingAddsToSubscriptionsList() {

        // given
        val clazz = Any::class
        given(bus.events(clazz.java)).willReturn(observable)
        given(observable.subscribe(any<Action1<Any>>())).willReturn(subscription)

        // when
        classToTest.subscribe(clazz, {})

        // then
        assertThat(classToTest.subscriptions.get(0), isEqualTo(subscription))
    }

    @Test
    fun subscribingSubscribesToBusEvents() {
        // given
        given(bus.events(Any::class.java)).willReturn(Observable.empty())

        // when
        classToTest.subscribe(Any::class, {})

        // then
        verify(bus).events(Any::class.java)
    }

    @Test
    fun unsubscribingUnsubscribesAndClearsSubscriptionsList() {

        // given
        classToTest.subscriptions.add(subscription)

        // when
        classToTest.unsubscribe()

        // then
        verify(subscription).unsubscribe()
        assertThat(classToTest.subscriptions.size, isEqualTo(0))
    }


}