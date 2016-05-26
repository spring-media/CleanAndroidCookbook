package pro.averin.anton.clean.android.cookbook.ui.common

import android.os.Bundle

interface MainLifecycleDelegate {

    fun onCreate(savedInstanceState: Bundle? = null) {
    }

    fun onStart() {
    }

    fun onResume() {
    }

    fun onPause() {
    }

    fun onSaveInstanceState(outState: Bundle?) {
    }

    fun onStop() {
    }

    fun onLowMemory() {

    }

    fun onDestroy() {
    }
}

