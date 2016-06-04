package pro.averin.anton.clean.android.cookbook.ui.main.presenter

import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.ExtraLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.UINavigator
import pro.averin.anton.clean.android.cookbook.ui.common.presenter.BasePresenter
import pro.averin.anton.clean.android.cookbook.ui.main.view.MainScreenContract
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionContract
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionDelegate
import javax.inject.Inject

@ActivityScope
class MainPresenter @Inject constructor(
) : BasePresenter<MainScreenContract>(), ExtraLifecycleDelegate, NavigationDrawerViewExtensionDelegate {

    @Inject lateinit var uiNavigator: UINavigator

    lateinit var navigationDrawerViewExtension: NavigationDrawerViewExtensionContract

    override fun showInitialScreen() {
        uiNavigator.showGoogleMapsScreen()
    }
}