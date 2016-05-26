package pro.averin.anton.clean.android.cookbook.ui.common.presenter

import pro.averin.anton.clean.android.cookbook.ui.common.view.ScreenContract

abstract class BasePresenter<V : ScreenContract> {
    var view: V? = null
}