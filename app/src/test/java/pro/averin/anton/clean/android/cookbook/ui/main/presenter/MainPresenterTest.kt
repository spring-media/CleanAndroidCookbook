package pro.averin.anton.clean.android.cookbook.ui.main.presenter

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.given
import pro.averin.anton.clean.android.cookbook.kotlin.test.verify
import pro.averin.anton.clean.android.cookbook.ui.common.ConnectionEventsHandler
import pro.averin.anton.clean.android.cookbook.ui.common.UINavigator
import pro.averin.anton.clean.android.cookbook.ui.common.resolution.Resolution
import pro.averin.anton.clean.android.cookbook.ui.main.view.MainScreenContract
import pro.averin.anton.clean.android.cookbook.ui.main.view.NavigationDrawerViewExtensionContract

@RunWith(PowerMockRunner::class)
@PrepareForTest(
        UINavigator::class,
        ConnectionEventsHandler::class
)
class MainPresenterTest {

    @Mock private lateinit var uiNavigator: UINavigator
    @Mock private lateinit var connectionEventsHandler: ConnectionEventsHandler
    @Mock private lateinit var navigationDrawerViewExtension: NavigationDrawerViewExtensionContract
    @Mock private lateinit var view: MainScreenContract
    @Mock private lateinit var resolution: Resolution
    @InjectMocks lateinit var classToTest: MainPresenter


    @Before
    fun setup() {
        classToTest.view = view
        given(view.getResolution()).willReturn(resolution)
    }

    @Test
    fun showsGoogleMapsAsInitialScreen() {
        // when
        classToTest.showInitialScreen()

        // then
        verify(uiNavigator).showGoogleMapsScreen()
    }

    @Test
    fun startsConnectionEventsHandlerOnCreate() {
        // when
        classToTest.onCreate()

        // then
        verify(connectionEventsHandler).start(resolution)
    }

    @Test
    fun stopsConnectionEventsHandlerOnDestroy() {
        // when
        classToTest.onDestroy()

        // then
        verify(connectionEventsHandler).stop()
    }

}