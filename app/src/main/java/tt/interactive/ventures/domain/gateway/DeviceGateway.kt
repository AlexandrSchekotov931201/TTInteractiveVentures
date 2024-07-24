package tt.interactive.ventures.domain.gateway

interface DeviceGateway {
    fun checkNetworkConnection(): Boolean
}