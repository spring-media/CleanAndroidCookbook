package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import pro.averin.anton.clean.android.cookbook.R
import javax.inject.Inject


open class UIResolution @Inject constructor(val uiResolver: UIResolver) : ResolutionByCase() {

    override fun onConnectivityAvailable() {
        uiResolver.hidePersistentSnackBar()
    }

    override fun onConnectivityUnavailable() {
        uiResolver.showPersistentSnackBar(R.string.error_no_network_connection)
    }

    override fun onNotFound() {
        uiResolver.showSnackBar(R.string.error_not_found)
    }

    override fun onServiceUnavailable() {
        uiResolver.showSnackBar(R.string.error_service_unavailable)
    }

    override fun onInternalServerError() {
        uiResolver.showSnackBar(R.string.error_http_exception)
    }

    override fun onGenericRxException(t: Throwable) {
        t.printStackTrace()
    }

    override fun onNetworkLocationError() {
        uiResolver.showSnackBar(R.string.error_enable_gps)
    }
}