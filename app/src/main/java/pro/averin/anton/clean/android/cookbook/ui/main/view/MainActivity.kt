package pro.averin.anton.clean.android.cookbook.ui.main.view

import android.databinding.DataBindingUtil
import android.support.v7.widget.Toolbar
import pro.averin.anton.clean.android.cookbook.R
import pro.averin.anton.clean.android.cookbook.databinding.ActivityMainBinding
import pro.averin.anton.clean.android.cookbook.di.ActivityComponent
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.UIResolution
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseActivity
import pro.averin.anton.clean.android.cookbook.ui.common.view.ResolvedScreenContract
import pro.averin.anton.clean.android.cookbook.ui.main.presenter.MainPresenter
import javax.inject.Inject

interface MainScreenContract : ResolvedScreenContract

class MainActivity : BaseActivity(), MainScreenContract {

    @Inject lateinit var presenter: MainPresenter
    @Inject lateinit var uiResolution: UIResolution
    @Inject lateinit var navigationDrawerViewExtension: NavigationDrawerViewExtension

    override fun doInjections(activityComponent: ActivityComponent) {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        activityComponent.injectTo(this)
        setMainLifecycleDelegates(presenter, navigationDrawerViewExtension)
        setExtraLifecycleDelegates(presenter)

        presenter.view = this
        presenter.navigationDrawerViewExtension = navigationDrawerViewExtension

        navigationDrawerViewExtension.setViews(binding.drawerLayout, binding.navigationView)

        presenter.showInitialScreen()

        setSupportActionBar(binding.toolbar.toolbar)
    }

    override fun setSupportActionBar(toolbar: Toolbar?) {
        super.setSupportActionBar(toolbar)
        toolbar?.let {
            navigationDrawerViewExtension.setToolbar(it)
        }
    }

    override fun getResolution(): Resolution? {
        return uiResolution
    }
}