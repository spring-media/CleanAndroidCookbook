package pro.averin.anton.clean.android.cookbook.ui.main.view

import android.app.Activity
import android.support.annotation.StringRes
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import javax.inject.Inject

class DrawerToggleFactory @Inject constructor() {

    fun create(
            activity: Activity,
            drawerLayout: DrawerLayout,
            toolbar: Toolbar,
            @StringRes openDrawerContentDescRes: Int,
            @StringRes closeDrawerContentDescRes: Int): ActionBarDrawerToggle {

        return ActionBarDrawerToggle(
                activity,
                drawerLayout,
                toolbar,
                openDrawerContentDescRes,
                closeDrawerContentDescRes);
    }
}
