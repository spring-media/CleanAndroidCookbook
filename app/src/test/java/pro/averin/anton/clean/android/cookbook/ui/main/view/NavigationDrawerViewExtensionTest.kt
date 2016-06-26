package pro.averin.anton.clean.android.cookbook.ui.main.view

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.view.menu.ActionMenuItem
import android.support.v7.widget.Toolbar
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.R
import pro.averin.anton.clean.android.cookbook.kotlin.test.*
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseActivity
import pro.averin.anton.clean.android.cookbook.ui.main.presenter.MainPresenter

@RunWith(PowerMockRunner::class)
@PrepareForTest(DrawerToggleFactory::class,
                MainPresenter::class)
class NavigationDrawerViewExtensionTest {


    @Mock lateinit private var activity: BaseActivity
    @Mock lateinit private var drawerToggleFactory: DrawerToggleFactory
    @Mock lateinit private var eventsDelegate: NavigationDrawerViewExtensionDelegate

    @Mock lateinit private var toolbar: Toolbar
    @Mock lateinit private var drawer: DrawerLayout
    @Mock lateinit private var navigationView: NavigationView
    @Mock lateinit private var drawerToggle: ActionBarDrawerToggle

    @InjectMocks private lateinit var classToTest: NavigationDrawerViewExtension

    @Captor lateinit private var captor: ArgumentCaptor<NavigationView.OnNavigationItemSelectedListener>

    @Before
    fun setup() {
        classToTest.eventsDelegate = eventsDelegate
    }

    @Test
    fun closeMenuOnBackIfOpen() {
        // given
        classToTest.setViews(drawer, navigationView)
        val gravity = GravityCompat.START
        given(drawer.isDrawerOpen(gravity)).willReturn(true)

        // when
        val result = classToTest.onBackPressed()

        // then
        verify(drawer).closeDrawer(gravity)
        assertThat(result, isTrue())
    }

    @Test
    fun processOnBackNormallyIfDrawerIsClosed() {
        // when
        val result = classToTest.onBackPressed()

        // then
        verify(drawer, never()).closeDrawer(any<Int>())
        assertThat(result, isFalse())
    }

    @Test
    fun drawerToggleIsCreatedAndInitializedAfterViewsWhereSet() {
        // given
        mockCreateDrawerToggle()
        classToTest.setToolbar(toolbar)

        // when
        classToTest.setViews(drawer, navigationView)

        // then
        verify(drawer).setDrawerListener(drawerToggle)
        verify(drawerToggle).syncState()
    }

    @Test
    fun listensForNavigationEvents() {
        // given
        mockCreateDrawerToggle()
        classToTest.setViews(drawer, navigationView)

        // when
        classToTest.onCreate()

        // then
        verify(navigationView).setNavigationItemSelectedListener(any())
    }


    private fun clickNavgationItem(itemId: Int) {
        mockCreateDrawerToggle()
        classToTest.onCreate(null)

        verify(navigationView).setNavigationItemSelectedListener(captor.capture())
        captor.value.onNavigationItemSelected(ActionMenuItem(null, 0, itemId, 0, 0, ""))
    }

    private fun mockCreateDrawerToggle() {
        given(drawerToggleFactory.create(
                activity,
                drawer,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        ).willReturn(drawerToggle)
    }
}