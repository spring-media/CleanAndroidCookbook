package pro.averin.anton.clean.android.cookbook.data.common.rx.bus.events

interface GlobalEvent : BusEvent

class ConnectivityAvailableGlobalEvent : GlobalEvent
class ConnectivityUnavailableGlobalEvent : GlobalEvent
