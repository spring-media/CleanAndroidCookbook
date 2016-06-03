package pro.averin.anton.clean.android.cookbook.data.common.rx

import rx.Scheduler


class MockSchedulers : Schedulers {
    override val io: Scheduler
        get() = rx.schedulers.Schedulers.immediate()
    override val mainThread: Scheduler
        get() = rx.schedulers.Schedulers.immediate()
    override val computation: Scheduler
        get() = rx.schedulers.Schedulers.immediate()
}