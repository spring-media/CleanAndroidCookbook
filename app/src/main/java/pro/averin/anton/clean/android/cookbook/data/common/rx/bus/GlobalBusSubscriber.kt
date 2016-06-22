package pro.averin.anton.clean.android.cookbook.data.common.rx.bus

import javax.inject.Inject

class GlobalBusSubscriber @Inject constructor(
        val bus: GlobalBus
) : BusSubscriber() {

    init {
        bus(bus)
    }
}