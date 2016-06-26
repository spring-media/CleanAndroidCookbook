package pro.averin.anton.clean.android.cookbook.ui.common.resolution

import android.R
import android.support.design.widget.Snackbar
import android.view.ViewGroup
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseActivity
import javax.inject.Inject


@ActivityScope
class UIResolver @Inject constructor(var baseActivity: BaseActivity) {

    private val snackbarRoot: ViewGroup

    init {
        snackbarRoot = baseActivity.findViewById(R.id.content) as ViewGroup
    }

    private var persistentSnackbar: Snackbar? = null

    fun showSnackBar(messageResource: Int) {
        Snackbar.make(snackbarRoot, messageResource, Snackbar.LENGTH_LONG).show()
    }

    fun showPersistentSnackBar(messageResource: Int) {
        persistentSnackbar = Snackbar.make(snackbarRoot, messageResource, Snackbar.LENGTH_INDEFINITE)
        persistentSnackbar?.show()
    }

    fun hidePersistentSnackBar() {
        persistentSnackbar?.dismiss()
    }

}