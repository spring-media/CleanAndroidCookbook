package pro.averin.anton.clean.android.cookbook.ui.common

import pro.averin.anton.clean.android.cookbook.data.common.connection.ConnectionChecker
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.GlobalBusSubscriber
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityAvailableGlobalEvent
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityUnavailableGlobalEvent
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import javax.inject.Inject

@ActivityScope
class ConnectionEventsHandler @Inject constructor(
        private val globalBusSubscriber: GlobalBusSubscriber,
        private val connectionChecker: ConnectionChecker
) {

    fun start(resolution: Resolution) {
        globalBusSubscriber.subscribe(ConnectivityAvailableGlobalEvent::class) {
            resolution.onConnectivityAvailable()
        }
        globalBusSubscriber.subscribe(ConnectivityUnavailableGlobalEvent::class) {
            resolution.onConnectivityUnavailable()
        }

        if (!connectionChecker.isOnline()) {
            resolution.onConnectivityUnavailable()
        } else {
            resolution.onConnectivityAvailable()
        }

    }

    fun stop() {
        globalBusSubscriber.unsubscribe()
    }

}
