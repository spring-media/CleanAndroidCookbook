package pro.averin.anton.clean.android.cookbook.ui.common

import android.content.Intent
import android.view.KeyEvent

interface ExtraLifecycleDelegate {
    fun onBackPressed(): Boolean {
        return false
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

    }

    fun onNewIntent(intent: Intent?) {

    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

    }

    fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }
}
