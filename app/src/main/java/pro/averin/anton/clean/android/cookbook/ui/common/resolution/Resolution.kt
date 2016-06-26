package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import retrofit2.adapter.rxjava.HttpException

interface RxHttpResolution {
    fun onHttpException(httpException: HttpException)
    fun onGenericRxException(t: Throwable)
}

interface NetworkConnectivityResolution {
    fun onConnectivityAvailable()
    fun onConnectivityUnavailable()
}

interface LocationRequestResolution {
    fun onNetworkLocationError()
}

interface Resolution : RxHttpResolution, NetworkConnectivityResolution, LocationRequestResolution {
}