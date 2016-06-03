package pro.averin.anton.clean.android.cookbook.ui.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import pro.averin.anton.clean.android.cookbook.R
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapsFragment
import javax.inject.Inject

@ActivityScope
class UINavigator @Inject constructor(
        private val fragmentManager: FragmentManager,
        private val fragmentBackstackChangeListener: FragmentBackStackChangeListener
) {

    companion object {
        const val CURRENT_FRAGMENT_TAG = "CURRENT_FRAGMENT_TAG"
    }

    init {
        fragmentBackstackChangeListener.onFragmentPopped = { parentFragment ->
            parentFragment.onResume()
        }
        fragmentBackstackChangeListener.onFragmentPushed = { parentFragment ->
            parentFragment.onPause()
        }
        fragmentManager.addOnBackStackChangedListener(fragmentBackstackChangeListener)
    }

    fun showGoogleMapsScreen() {
        showFragment(GoogleMapsFragment.create())
    }


    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        clearBackStack()
        val transaction = fragmentManager
                .beginTransaction()
                .replace(R.id.fl_content, fragment, UINavigator.Companion.CURRENT_FRAGMENT_TAG)

        if (addToBackStack)
            transaction.addToBackStack(UINavigator.Companion.CURRENT_FRAGMENT_TAG)

        transaction.commit()
    }

    private fun clearBackStack() {
        if (fragmentManager.backStackEntryCount > 0) {
            (1..fragmentManager.backStackEntryCount).forEach {
                fragmentManager.popBackStack()
            }
        }
    }
}