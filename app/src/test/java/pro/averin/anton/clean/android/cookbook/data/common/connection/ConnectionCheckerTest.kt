package pro.averin.anton.clean.android.cookbook.data.common.connection

import android.net.ConnectivityManager
import android.net.NetworkInfo
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.powermock.modules.junit4.PowerMockRunner
import pro.averin.anton.clean.android.cookbook.kotlin.test.assertThat
import pro.averin.anton.clean.android.cookbook.kotlin.test.given
import pro.averin.anton.clean.android.cookbook.kotlin.test.isEqualTo

@RunWith(PowerMockRunner::class)
class ConnectionCheckerTest {

    @Mock lateinit var connectivityManager: ConnectivityManager
    @Mock lateinit var activeNetworkInfo: NetworkInfo

    @InjectMocks lateinit var classToTest: ConnectionChecker

    @Test
    fun networkStatusOfflineWhenNoNetworksConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(null)

        // when
        val status = classToTest.getNetworkStatus()

        // then
        assertThat(status, isEqualTo(ConnectionChecker.OFFLINE))
    }

    @Test
    fun networkStatusOfflineWhenNotWifiOrMobileNetworkConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(activeNetworkInfo)
        given(activeNetworkInfo.isConnectedOrConnecting).willReturn(true)
        given(activeNetworkInfo.type).willReturn(ConnectivityManager.TYPE_DUMMY)

        // when
        val status = classToTest.getNetworkStatus()

        // then
        assertThat(status, isEqualTo(ConnectionChecker.OFFLINE))
    }

    @Test
    fun networkStatusWifiWhenWifiConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(activeNetworkInfo)
        given(activeNetworkInfo.type).willReturn(ConnectivityManager.TYPE_WIFI)
        given(activeNetworkInfo.isConnectedOrConnecting).willReturn(true)

        // when
        val status = classToTest.getNetworkStatus()

        // then
        assertThat(status, isEqualTo(ConnectionChecker.WIFI))
    }

    @Test
    fun offlineWhenActiveNetworkNotConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(activeNetworkInfo)
        given(activeNetworkInfo.isConnectedOrConnecting).willReturn(false)

        // when
        val result = classToTest.isOnline()

        // then
        assertThat(result, isEqualTo(false))
    }


    @Test
    fun onlineWhenActiveNetworkConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(activeNetworkInfo)
        given(activeNetworkInfo.isConnectedOrConnecting).willReturn(true)

        // when
        val result = classToTest.isOnline()

        // then
        assertThat(result, isEqualTo(true))
    }

    @Test
    fun networkStatusMobileWhenMobileConnected() {
        // given
        given(connectivityManager.activeNetworkInfo).willReturn(activeNetworkInfo)
        given(activeNetworkInfo.type).willReturn(ConnectivityManager.TYPE_MOBILE)
        given(activeNetworkInfo.isConnectedOrConnecting).willReturn(true)

        // when
        val status = classToTest.getNetworkStatus()

        // then
        assertThat(status, isEqualTo(ConnectionChecker.MOBILE))
    }
}