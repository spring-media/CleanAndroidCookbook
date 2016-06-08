package pro.averin.anton.clean.android.cookbook.ui.common.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import com.mikepenz.iconics.context.IconicsContextWrapper
import pro.averin.anton.clean.android.cookbook.BaseContext
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.di.ActivityModule
import pro.averin.anton.clean.android.cookbook.di.DaggerActivityComponent
import pro.averin.anton.clean.android.cookbook.ui.common.ExtraLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.MainLifecycleDelegate


abstract class BaseActivity : AppCompatActivity() {
    private var mainLifecycleDelegates: Array<out MainLifecycleDelegate> = arrayOf()
    private var extraLifecycleDelegates: Array<out ExtraLifecycleDelegate> = arrayOf()

    protected open fun setMainLifecycleDelegates(vararg mainDelegates: MainLifecycleDelegate) {
        mainLifecycleDelegates = mainDelegates
    }

    protected open fun setExtraLifecycleDelegates(vararg extraDelegates: ExtraLifecycleDelegate) {
        extraLifecycleDelegates = extraDelegates
    }

    lateinit var activityComponent: ActivityComponent

    abstract fun doInjections(activityComponent: ActivityComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseContext = applicationContext as BaseContext
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(baseContext.appComponent)
                .activityModule(ActivityModule(this))
                .build();

        doInjections(activityComponent)

        mainLifecycleDelegates.forEach {
            it.onCreate(savedInstanceState)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(IconicsContextWrapper.wrap(newBase))
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

    override fun onStop() {
        super.onStop()
        mainLifecycleDelegates.forEach { it.onStop() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainLifecycleDelegates.forEach { it.onDestroy() }
    }


    override fun onBackPressed() {
        extraLifecycleDelegates.forEach {
            if (it.onBackPressed()) {
                return
            }
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        extraLifecycleDelegates.forEach {
            if (it.onKeyUp(keyCode, event)) {
                return true
            }
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        extraLifecycleDelegates.forEach {
            it.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onNewIntent(intent: Intent?) {
        extraLifecycleDelegates.forEach {
            it.onNewIntent(intent)
        }
        super.onNewIntent(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        extraLifecycleDelegates.forEach {
            it.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}