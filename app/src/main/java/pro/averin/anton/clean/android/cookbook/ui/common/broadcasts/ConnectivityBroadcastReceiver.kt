package pro.averin.anton.clean.android.cookbook.ui.common.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import pro.averin.anton.clean.android.cookbook.BaseContext
import pro.averin.anton.clean.android.cookbook.data.common.connection.ConnectionChecker
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.GlobalBus
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityAvailableGlobalEvent
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.ConnectivityUnavailableGlobalEvent
import javax.inject.Inject

class ConnectivityBroadcastReceiver : BroadcastReceiver() {

    @Inject lateinit var connectionChecker: ConnectionChecker
    @Inject lateinit var globalBus: GlobalBus

    override fun onReceive(context: Context?, intent: Intent?) {
        val baseContext = context?.applicationContext as BaseContext
        baseContext.appComponent.injectTo(this)

        if (connectionChecker.isOnline()) {
            globalBus.post(ConnectivityAvailableGlobalEvent())
        } else {
            globalBus.post(ConnectivityUnavailableGlobalEvent())
        }
    }
}