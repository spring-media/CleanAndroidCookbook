package pro.averin.anton.clean.android.cookbook.ui.main.view

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import pro.averin.anton.clean.android.cookbook.di.ActivityScope
import pro.averin.anton.clean.android.cookbook.ui.common.ExtraLifecycleDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseActivity
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegate
import pro.averin.anton.clean.android.cookbook.ui.common.view.EventsDelegatingViewExtension
import javax.inject.Inject


interface NavigationDrawerViewExtensionDelegate : EventsDelegate {
    fun showInitialScreen()
}

interface NavigationDrawerViewExtensionContract

@ActivityScope
class NavigationDrawerViewExtension @Inject constructor(
        private val activity: BaseActivity,
        private val drawerToggleFactory: DrawerToggleFactory
) : EventsDelegatingViewExtension<NavigationDrawerViewExtensionDelegate>, NavigationDrawerViewExtensionContract, ExtraLifecycleDelegate {

    override var eventsDelegate: NavigationDrawerViewExtensionDelegate? = null

    private var toolbar: Toolbar? = null
    private var drawer: DrawerLayout? = null
    private lateinit var navigationView: NavigationView

    fun setViews(
            drawer: DrawerLayout,
            navigationView: NavigationView) {
        this.drawer = drawer;
        this.navigationView = navigationView
        createDrawerToggle()
    }

    fun setToolbar(toolbar: Toolbar) {
        this.toolbar = toolbar
        createDrawerToggle()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        navigationView.setNavigationItemSelectedListener { onNavigationItemSelected(it.itemId) }
        eventsDelegate?.showInitialScreen()
    }


    override fun onBackPressed(): Boolean {
        if (drawer?.isDrawerOpen(GravityCompat.START) ?: false) {
            drawer?.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    private fun createDrawerToggle() {

        if (drawer == null || toolbar == null) return

        val toggle = drawerToggleFactory.create(
                activity,
                drawer!!,
                toolbar!!,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)

        drawer!!.setDrawerListener(toggle)
        toggle.syncState()
    }

    private fun onNavigationItemSelected(id: Int): Boolean {

        when (id) {
//            R.id.nav1 -> eventsDelegate?.showNav1Screen()
//            R.id.nav2 -> eventsDelegate?.showNav2Screen()
        }

        drawer?.closeDrawer(GravityCompat.START)
        return true
    }
}
