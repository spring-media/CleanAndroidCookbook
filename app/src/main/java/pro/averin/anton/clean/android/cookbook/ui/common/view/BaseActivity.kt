package pro.averin.anton.clean.android.cookbook.ui.common.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import pro.averin.anton.clean.android.cookbook.BaseContext
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.di.ActivityModule
import pro.averin.anton.clean.android.cookbook.di.DaggerActivityComponent


class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val baseContext = applicationContext as BaseContext
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(baseContext.appComponent)
                .activityModule(ActivityModule(this))
                .build();
    }

}