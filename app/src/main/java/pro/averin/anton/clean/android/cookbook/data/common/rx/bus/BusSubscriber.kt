package pro.averin.anton.clean.android.cookbook.data.common.rx.bus

import rx.Subscription
import javax.inject.Inject
import kotlin.reflect.KClass

interface BusSubscriberContract {
    fun bus(bus: RxBus)
    fun <T : Any> subscribe(clazz: KClass<T>, callback: (T) -> Unit)
    fun unsubscribe()
}

open class BusSubscriber @Inject constructor() : BusSubscriberContract {

    val subscriptions = mutableListOf<Subscription>()

    private lateinit var bus: RxBus
    override fun bus(bus: RxBus) {
        this.bus = bus
    }

    override fun <T : Any> subscribe(clazz: KClass<T>, callback: (T) -> Unit) {
        subscriptions.add(bus.events(clazz.java).subscribe(callback))

    }

    override fun unsubscribe() {
        subscriptions.forEach { it.unsubscribe() }
        subscriptions.clear()
    }
}