package pro.averin.anton.clean.android.cookbook.ui.common.view

import pro.averin.anton.clean.android.cookbook.ui.common.MainLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution


interface ViewContract

interface ScreenContract : ViewContract

interface ResolvedScreenContract : ScreenContract {
    fun getResolution(): Resolution?
}

interface ViewExtension : MainLifecycleDelegate

interface EventsDelegate
interface EventsDelegatingViewExtension<D : EventsDelegate> : ViewExtension {
    var eventsDelegate: D?
}