package pro.averin.anton.clean.android.cookbook.data.common.rx.bus

import pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events.BusEvent
import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject

open class RxBus {
    private val bus = SerializedSubject<BusEvent, BusEvent>(PublishSubject.create());

    fun post(event: BusEvent) {
        bus.onNext(event)
    }

    fun <T> events(type: Class<T>): Observable<T> {
        return events().ofType(type)
    }

    fun events(): Observable<BusEvent> {
        return bus.asObservable()
    }
}