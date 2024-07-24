package tt.interactive.ventures.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import tt.interactive.core.di.AppScope
import tt.interactive.ventures.data.gateway.ChallengeGatewayImpl
import tt.interactive.ventures.domain.gateway.ChallengeGateway
import tt.interactive.ventures.domain.usecase.GetChartPointsUseCaseImpl
import tt.interactive.ventures.domain.usecase.GetNetworkAvailabilityUseCaseImpl
import tt.interactive.ventures.domain.usecase.LoadChartPointsUseCaseImpl
import tt.interactive.ventures.ui.usecase.GetChartPointsUseCase
import tt.interactive.ventures.ui.usecase.GetNetworkAvailabilityUseCase
import tt.interactive.ventures.ui.usecase.LoadChartPointsUseCase

@Module(includes = [AppModule.AppBindsModule::class])
class AppModule {

    @Provides
    @AppScope
    fun provideContext(application: Application): Context = application.applicationContext

    @Module
    interface AppBindsModule {
        @Binds
        @AppScope
        fun bindChallengeGateway(impl: ChallengeGatewayImpl): ChallengeGateway

        @Binds
        @AppScope
        fun bindGetChartPointsUseCase(impl: GetChartPointsUseCaseImpl): GetChartPointsUseCase

        @Binds
        @AppScope
        fun bindLoadChartPointsUseCase(impl: LoadChartPointsUseCaseImpl): LoadChartPointsUseCase

        @Binds
        @AppScope
        fun bindGetNetworkAvailabilityUseCase(impl: GetNetworkAvailabilityUseCaseImpl): GetNetworkAvailabilityUseCase
    }

}