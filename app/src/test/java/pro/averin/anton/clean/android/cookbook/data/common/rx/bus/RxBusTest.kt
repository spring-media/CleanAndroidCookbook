package pro.averin.anton.clean.android.cookbook.data.common.rx.bus


import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.BusEvent

@RunWith(PowerMockRunner::class)
class RxBusTest {

    val classToTest = RxBus()

    @Test
    fun postingEventOnBusAppearsInEvents() {
        // given
        var testEvent = TestEvent()
        var resultEvent: Any? = null
        classToTest.events().subscribe {
            resultEvent = it
        }

        // when
        classToTest.post(testEvent)

        // then
        Assert.assertThat(resultEvent as? TestEvent, CoreMatchers.`is`(testEvent))
    }

    @Test
    fun postingEventOnBusAppearsInEventsForType() {
        // given
        var testEvent = TestEvent()
        var resultEvent: Any? = null
        classToTest.events(TestEvent::class.java).subscribe {
            resultEvent = it
        }

        // when
        classToTest.post(testEvent)

        // then
        Assert.assertThat(resultEvent as? TestEvent, CoreMatchers.`is`(testEvent))
    }

    class TestEvent : BusEvent

}