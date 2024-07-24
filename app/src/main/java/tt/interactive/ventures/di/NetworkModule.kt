package tt.interactive.ventures.di

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tt.interactive.core.di.AppScope
import tt.interactive.ventures.domain.gateway.DeviceGateway
import tt.interactive.ventures.data.gateway.DeviceGatewayImpl
import tt.interactive.ventures.data.network.api.ChallengeApi

@Module
class NetworkModule {

    @Provides
    @AppScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://hr-challenge.dev.tapyou.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @AppScope
    fun provideConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @AppScope
    fun provideNetworkConnectionObserver(connectivityManager: ConnectivityManager): DeviceGateway {
        return DeviceGatewayImpl(connectivityManager)
    }

    @Provides
    @AppScope
    fun provideChallengeApi(retrofit: Retrofit): ChallengeApi {
        return retrofit.create(ChallengeApi::class.java)
    }

}