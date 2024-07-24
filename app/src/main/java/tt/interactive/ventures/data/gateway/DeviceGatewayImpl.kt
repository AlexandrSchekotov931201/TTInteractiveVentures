package tt.interactive.ventures.data.gateway

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import tt.interactive.ventures.domain.gateway.DeviceGateway
import javax.inject.Inject

class DeviceGatewayImpl @Inject constructor(
    private val connectivityManager: ConnectivityManager
) : DeviceGateway {
    override fun checkNetworkConnection(): Boolean {
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}