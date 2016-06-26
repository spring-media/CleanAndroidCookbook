package pro.averin.anton.clean.android.cookbook.ui.main.presenter

import android.os.Bundle
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.ConnectionEventsHandler
import pro.averin.anton.clean.android.cookbook.ui.common.ExtraLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.UINavigator
import pro.averin.anton.clean.android.cookbook.ui.common.presenter.BasePresenter
import pro.averin.anton.clean.android.cookbook.ui.main.view.MainScreenContract
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionContract
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionDelegate
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject constructor(
        private val connectionEventsHandler: ConnectionEventsHandler,
        private val uiNavigator: UINavigator
) : BasePresenter<MainScreenContract>(), ExtraLifecycleDelegate, NavigationDrawerViewExtensionDelegate {
    
    lateinit var navigationDrawerViewExtension: NavigationDrawerViewExtensionContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        connectionEventsHandler.start(view?.getResolution()!!)
    }

    override fun onDestroy() {
        super.onDestroy()
        connectionEventsHandler.stop()
    }

    override fun showInitialScreen() {
        uiNavigator.showGoogleMapsScreen()
    }
}