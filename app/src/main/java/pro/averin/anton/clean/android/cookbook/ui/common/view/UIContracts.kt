package pro.averin.anton.clean.android.cookbook.ui.common.view


interface ViewContract

interface ScreenContract : ViewContract

interface ViewExtension

interface EventsDelegate
interface EventsDelegatingViewExtension<D : EventsDelegate> : ViewExtension {
    var eventsDelegate: D?
}