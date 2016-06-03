package pro.averin.anton.clean.android.cookbook.ui.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.*
import pro.averin.anton.clean.android.cookbook.ui.common.view.BaseFragment
import pro.averin.anton.clean.android.cookbook.ui.googlemaps.view.GoogleMapsFragment

@RunWith(PowerMockRunner::class)
@PrepareForTest()
class UINavigatorTest {

    @Mock private lateinit var fragmentManager: FragmentManager
    @Mock private lateinit var fragmentTransaction: FragmentTransaction
    @Mock private lateinit var fragment: BaseFragment
    @Mock private lateinit var fragmentBackstackChangeListener: FragmentBackStackChangeListener

    @InjectMocks lateinit var classToTest: UINavigator


    @Before
    fun setup() {
        given(fragmentManager.beginTransaction()).willReturn(fragmentTransaction)
        given(fragmentTransaction.replace(any(), any(), any())).willReturn(fragmentTransaction)
        given(fragmentTransaction.addToBackStack(any())).willReturn(fragmentTransaction)
    }

    @Test
    fun showsGoogleMapsScreen() {
        // when
        classToTest.showGoogleMapsScreen()

        // then
        verify(fragmentTransaction.replace(any(), argThat { this is GoogleMapsFragment }, any()))
    }

    @Test
    fun creatingNavigatorSetsBackstackListener() {
        // when
        reset(fragmentManager)
        reset(fragmentBackstackChangeListener)
        classToTest = UINavigator(
                fragmentManager,
                fragmentBackstackChangeListener
        )

        // then
        verify(fragmentManager).addOnBackStackChangedListener(fragmentBackstackChangeListener)
    }

    @Test
    fun backStackFragmentPopResumesParent() {
        // given

        reset(fragmentManager)
        reset(fragment)
        reset(fragmentBackstackChangeListener)
        classToTest = UINavigator(
                fragmentManager,
                fragmentBackstackChangeListener
        )
        var fragmentPoppedCallback: ((Fragment) -> Unit)? = null
        verify(fragmentBackstackChangeListener, twice()).onFragmentPopped = argThat {
            fragmentPoppedCallback = this
            true
        }

        // when
        fragmentPoppedCallback!!.invoke(fragment)

        // then
        verify(fragment).onResume()
    }

    @Test
    fun backStackFragmentPushPausesParent() {
        // given
        classToTest = UINavigator(
                fragmentManager,
                fragmentBackstackChangeListener
        )
        reset(fragmentManager)
        reset(fragment)
        reset(fragmentBackstackChangeListener)
        var fragmentPushedCallback: ((Fragment) -> Unit)? = null
        verify(fragmentBackstackChangeListener, twice()).onFragmentPushed = argThat {
            fragmentPushedCallback = this
            true
        }

        // when
        fragmentPushedCallback!!.invoke(fragment)

        // then
        verify(fragment).onPause()
    }

}