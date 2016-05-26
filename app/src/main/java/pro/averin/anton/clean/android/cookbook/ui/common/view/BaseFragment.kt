package pro.averin.anton.clean.android.cookbook.ui.common.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.ui.common.ExtraLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.MainLifecycleDelegate
import java.util.*

abstract class BaseFragment : Fragment() {

    private var mainLifecycleDelegates: List<MainLifecycleDelegate> = LinkedList()
    private var extraLifecycleDelegates: List<ExtraLifecycleDelegate> = LinkedList()

    protected lateinit var baseActivity: BaseActivity

    fun setMainLifecycleDelegates(mainDelegates: List<MainLifecycleDelegate>) {
        mainLifecycleDelegates = mainDelegates
    }

    fun setExtraLifecycleDelegates(extraDelegates: List<ExtraLifecycleDelegate>) {
        extraLifecycleDelegates = extraDelegates
    }

    abstract fun doInjections(activityComponent: ActivityComponent)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val baseActivity = activity as? BaseActivity
        if (baseActivity != null) {
            this.baseActivity = baseActivity
            doInjections(baseActivity.activityComponent)
        }

        mainLifecycleDelegates.forEach { it.onCreate(savedInstanceState) }
    }

    override fun onStart() {
        super.onStart()
        mainLifecycleDelegates.forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        mainLifecycleDelegates.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        mainLifecycleDelegates.forEach { it.onPause() }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        mainLifecycleDelegates.forEach { it.onSaveInstanceState(outState) }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mainLifecycleDelegates.forEach { it.onLowMemory() }
    }

    override fun onStop() {
        super.onStop()
        mainLifecycleDelegates.forEach { it.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainLifecycleDelegates.forEach { it.onDestroy() }
    }

    fun onBackPressed(): Boolean {
        extraLifecycleDelegates.forEach {
            return it.onBackPressed()
        }
        return false
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        extraLifecycleDelegates.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        extraLifecycleDelegates.forEach {
            it.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}