package pro.averin.anton.clean.android.cookbook.ui.common.rx

import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.BusSubscriber
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import javax.inject.Inject

@ActivityScope
class ActivityBusSubscriber @Inject constructor(
        val bus: ActivityBus
) : BusSubscriber() {

    init {
        bus(bus)
    }
}