package tt.interactive.ventures.ui.chartscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tt.interactive.core.di.ScreenScope
import tt.interactive.core.di.ViewModelKey
import tt.interactive.core.ui.viewmodel.ViewModelFactory
import tt.interactive.ventures.ui.chartscreen.viewmodel.ChartScreenViewModel

@Module
abstract class ChartScreenModule {
    @Binds
    @ScreenScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ScreenScope
    @IntoMap
    @ViewModelKey(ChartScreenViewModel::class)
    abstract fun provideViewModel(viewModel: ChartScreenViewModel): ViewModel
}