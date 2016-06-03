package pro.averin.anton.clean.android.cookbook.ui.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import javax.inject.Inject


class FragmentBackStackChangeListener @Inject constructor(
        private val fragmentManager: FragmentManager
) : FragmentManager.OnBackStackChangedListener {

    var lastBackStackEntryCount: Int

    init {
        lastBackStackEntryCount = fragmentManager.backStackEntryCount
    }

    lateinit var onFragmentPushed: (Fragment) -> Unit
    lateinit var onFragmentPopped: (Fragment) -> Unit

    override fun onBackStackChanged() {
        val currentBackStackEntryCount = fragmentManager.backStackEntryCount
        if (hasFragments()) {
            val parentFragment = getParentFragment()
            parentFragment?.let {
                if (wasPushed(currentBackStackEntryCount)) {
                    onFragmentPushed(parentFragment)
                } else if (wasPopped(currentBackStackEntryCount)) {
                    onFragmentPopped(parentFragment)
                }
            }
        }

        lastBackStackEntryCount = currentBackStackEntryCount
    }

    private fun wasPushed(backStackEntryCount: Int): Boolean {
        return lastBackStackEntryCount < backStackEntryCount
    }

    private fun wasPopped(backStackEntryCount: Int): Boolean {
        return lastBackStackEntryCount > backStackEntryCount
    }

    private fun hasFragments(): Boolean {
        return !fragmentManager.fragments?.isEmpty()!!
    }

    private fun getParentFragment(): Fragment? {
        val fragments = fragmentManager.fragments
        return fragments[Math.max(0, fragments.size - 2)]
    }
}