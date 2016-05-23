package pro.averin.anton.clean.android.cookbook.di

import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import dagger.Module
import dagger.Provides
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseActivity


@Module
class ActivityModule(private val baseActivity: BaseActivity) {

    @Provides
    @ActivityScope
    fun baseActivity(): BaseActivity {
        return baseActivity
    }

    @Provides
    @ActivityScope
    fun layoutInflater(): LayoutInflater {
        return baseActivity.layoutInflater
    }

    @Provides
    @ActivityScope
    fun fragmentManager(): FragmentManager {
        return baseActivity.supportFragmentManager
    }
}