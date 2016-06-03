package pro.averin.anton.clean.android.cookbook.data.common.rx

import rx.Scheduler

interface Schedulers {
    val io: Scheduler
    val mainThread: Scheduler
    val computation: Scheduler
}