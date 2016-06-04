package pro.averin.anton.clean.android.cookbook.data.common.rx

import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RxSchedulers @Inject constructor() : Schedulers {

    override val io: Scheduler
        get() = rx.schedulers.Schedulers.io()
    override val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()
    override val computation: Scheduler
        get() = rx.schedulers.Schedulers.computation()
}
