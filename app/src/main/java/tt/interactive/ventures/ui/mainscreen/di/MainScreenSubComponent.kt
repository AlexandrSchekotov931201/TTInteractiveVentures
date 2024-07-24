package tt.interactive.ventures.ui.mainscreen.di

import dagger.Subcomponent
import tt.interactive.core.di.ScreenScope
import tt.interactive.ventures.ui.mainscreen.MainScreenFragment

@ScreenScope
@Subcomponent(modules = [MainScreenModule::class])
interface MainScreenSubComponent {
    fun inject(screen: MainScreenFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainScreenSubComponent
    }
}