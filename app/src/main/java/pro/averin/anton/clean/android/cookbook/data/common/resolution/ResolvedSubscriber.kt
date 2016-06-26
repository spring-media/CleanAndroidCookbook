package pro.averin.anton.clean.android.cookbook.data.common.resolution

import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber


class ResolvedSubscriber<T> constructor(
        val resolution: Resolution,
        val onNextFunc: (T) -> Unit,
        val onCompletedFunc: () -> Unit = {},
        val onErrorFunc: (Throwable?) -> Unit = {}) : Subscriber<T>() {

    override fun onNext(p0: T) {
        if (!isUnsubscribed) {
            onNextFunc(p0)
        }
    }

    override fun onCompleted() {
        onCompletedFunc()
    }

    override fun onError(throwable: Throwable?) {
        when (throwable) {
            is HttpException -> resolution.onHttpException(throwable)
        // let your location implementation throw a custom exception on timeout, for example
//            is NetworkLocationTimeoutException -> resolution.onNetworkLocationError()
            else -> throwable?.apply { resolution.onGenericRxException(this) }
        }

        onErrorFunc(throwable)
    }

}