package tt.interactive.ventures.ui.chartscreen.di

import dagger.Subcomponent
import tt.interactive.core.di.ScreenScope
import tt.interactive.ventures.ui.chartscreen.ChartScreenFragment

@ScreenScope
@Subcomponent(modules = [ChartScreenModule::class])
interface ChartScreenSubComponent {
    fun inject(screen: ChartScreenFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): ChartScreenSubComponent
    }
}