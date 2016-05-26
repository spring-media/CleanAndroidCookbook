package pro.averin.anton.clean.android.cookbook.ui.common.view

import pro.averin.anton.clean.android.cookbook.ui.common.MainLifecycleDelegate


interface ViewContract

interface ScreenContract : ViewContract

interface ViewExtension : MainLifecycleDelegate

interface EventsDelegate
interface EventsDelegatingViewExtension<D : EventsDelegate> : ViewExtension {
    var eventsDelegate: D?
}