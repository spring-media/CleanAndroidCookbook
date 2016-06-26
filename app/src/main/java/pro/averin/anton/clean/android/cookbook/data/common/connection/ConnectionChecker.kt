package pro.averin.anton.clean.android.cookbook.data.common.connection

import android.net.ConnectivityManager
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ConnectionChecker @Inject constructor() {

    @Inject lateinit var connectivityManager: ConnectivityManager

    fun getNetworkStatus(): Int {
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnectedOrConnecting) {
            when (networkInfo.type) {
                ConnectivityManager.TYPE_WIFI -> return WIFI
                ConnectivityManager.TYPE_MOBILE -> return MOBILE
                else -> return OFFLINE
            }
        }

        return OFFLINE;
    }

    fun isOnline(): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }


    companion object NetworkStatus {
        val OFFLINE = 1
        val MOBILE = 2
        val WIFI = 3
    }
}