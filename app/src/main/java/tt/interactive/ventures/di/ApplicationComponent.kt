package tt.interactive.ventures.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import tt.interactive.core.di.AppScope
import tt.interactive.ventures.ui.MainActivity
import tt.interactive.ventures.ui.chartscreen.di.ChartScreenSubComponent
import tt.interactive.ventures.ui.mainscreen.di.MainScreenSubComponent

@AppScope
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun mainScreenSubComponent(): MainScreenSubComponent.Factory
    fun chartScreenSubComponent(): ChartScreenSubComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}