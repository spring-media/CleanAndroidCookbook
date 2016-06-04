package pro.averin.anton.clean.android.cookbook.ui.main.presenter

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import pro.averin.anton.clean.android.cookbook.ui.common.UINavigator
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionContract

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        UINavigator::class
)
class MainPresenterTest {

    @Mock private lateinit var uiNavigator: UINavigator
    @Mock private lateinit var navigationDrawerViewExtension: NavigationDrawerViewExtensionContract

    @InjectMocks lateinit var classToTest: MainPresenter

    @Test
    fun showsGoogleMapsAsInitialScreen() {
        // when
        classToTest.showInitialScreen()

        // then
        verify(uiNavigator).showGoogleMapsScreen()
    }

}