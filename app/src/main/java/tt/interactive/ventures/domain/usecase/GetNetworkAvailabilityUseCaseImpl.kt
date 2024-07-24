package tt.interactive.ventures.domain.usecase

import tt.interactive.ventures.ui.usecase.GetNetworkAvailabilityUseCase
import tt.interactive.ventures.domain.gateway.DeviceGateway
import javax.inject.Inject

class GetNetworkAvailabilityUseCaseImpl @Inject constructor(
    private val deviceGateway: DeviceGateway
) : GetNetworkAvailabilityUseCase {
    override operator fun invoke(): Boolean {
        return deviceGateway.checkNetworkConnection()
    }
}