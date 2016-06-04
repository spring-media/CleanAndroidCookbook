package pro.averin.anton.clean.android.cookbook.ui.common

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.*

@RunWith(PowerMockRunner::class)
@PrepareForTest()
class FragmentBackStackChangeListenerTest {

    @Mock private lateinit var fragmentManager: FragmentManager

    @InjectMocks private lateinit var classToTest: FragmentBackStackChangeListener

    private var parentFragment: Fragment? = Fragment()
    private val fragments = listOf(
            parentFragment,
            Fragment()
    )

    private var fragmentPopped = false
    private var fragmentPushed = false

    private val initialBackStackEntryCount = 3

    @Before
    fun setup() {
        classToTest.lastBackStackEntryCount = initialBackStackEntryCount
        classToTest.onFragmentPopped = {
            fragmentPopped = true
        }
        classToTest.onFragmentPushed = {
            fragmentPushed = true
        }
        given(fragmentManager.fragments).willReturn(fragments)
    }

    @Test
    fun updateLastBackstackEntryCountOnChange() {
        // given
        given(fragmentManager.backStackEntryCount).willReturn(fragments.size)

        // when
        classToTest.onBackStackChanged()

        // then
        assertThat(classToTest.lastBackStackEntryCount, isEqualTo(fragments.size))
    }

    @Test
    fun doesNothingWhenNoFragments() {
        // given
        given(fragmentManager.fragments).willReturn(emptyList())

        // when
        classToTest.onBackStackChanged()

        // then
        assertThat(fragmentPopped, isFalse())
        assertThat(fragmentPushed, isFalse())
    }

    @Test
    fun doesNothingWhenNoParent() {
        // given
        parentFragment = null
        given(fragmentManager.fragments).willReturn(listOf(
                parentFragment,
                Fragment()
        ))

        // when
        classToTest.onBackStackChanged()

        // then
        assertThat(fragmentPopped, isFalse())
        assertThat(fragmentPushed, isFalse())
    }

    @Test
    fun notifiesFragmentPushed() {
        // given
        given(fragmentManager.backStackEntryCount).willReturn(4)

        // when
        classToTest.onBackStackChanged()

        // then
        assertThat(fragmentPushed, isTrue())
        assertThat(fragmentPopped, isFalse())
    }

    @Test
    fun notifiesFragmentPopped() {
        // given
        given(fragmentManager.backStackEntryCount).willReturn(2)

        // when
        classToTest.onBackStackChanged()

        // then
        assertThat(fragmentPushed, isFalse())
        assertThat(fragmentPopped, isTrue())
    }
}