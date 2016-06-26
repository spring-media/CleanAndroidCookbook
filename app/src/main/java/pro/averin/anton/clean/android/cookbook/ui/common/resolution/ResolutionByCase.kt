package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import retrofit2.adapter.rxjava.HttpException


abstract class ResolutionByCase : Resolution {

    override fun onHttpException(httpException: HttpException) {
        val code = httpException.code()
        when (code) {
            500 -> onInternalServerError()
            503 -> onServiceUnavailable()
            404 -> onNotFound()
            else -> onInternalServerError()
        }
    }

    abstract fun onInternalServerError()

    abstract fun onNotFound()

    abstract fun onServiceUnavailable()
}