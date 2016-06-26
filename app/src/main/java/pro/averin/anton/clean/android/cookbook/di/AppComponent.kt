package pro.averin.anton.clean.android.cookbook.di

import android.net.ConnectivityManager
import com.tbruyelle.rxpermissions.RxPermissions
import dagger.Component
import pro.averin.anton.clean.android.cookbook.BaseContext
import pro.averin.anton.clean.android.cookbook.data.common.connection.ConnectionChecker
import pro.averin.anton.clean.android.cookbook.data.common.rx.Schedulers
import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.GlobalBusSubscriber
import pro.averin.anton.clean.android.cookbook.data.flickr.FlickrRepo
import pro.averin.anton.clean.android.cookbook.ui.common.broadcasts.ConnectivityBroadcastReceiver
import pro.averin.anton.clean.android.cookbook.ui.common.map.MapUtils
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        WebServicesModule::class,
        SystemServicesModule::class
))
interface AppComponent {
    //here we list things that are of Singleton-scope and should be accessible by other scopes
    fun baseContext(): BaseContext

    fun flickrRepo(): FlickrRepo

    fun rxSchedulers(): Schedulers
    fun rxPermissions(): RxPermissions

    fun globalBusSubscriber(): GlobalBusSubscriber

    fun mapUtils(): MapUtils

    fun connectionChecker(): ConnectionChecker
    fun connectivityManager(): ConnectivityManager
    fun injectTo(connectivityBroadcastReceiver: ConnectivityBroadcastReceiver)
}