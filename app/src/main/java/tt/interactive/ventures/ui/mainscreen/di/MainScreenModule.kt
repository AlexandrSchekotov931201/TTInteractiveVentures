package tt.interactive.ventures.ui.mainscreen.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import tt.interactive.core.di.ScreenScope
import tt.interactive.core.di.ViewModelKey
import tt.interactive.core.ui.viewmodel.ViewModelFactory
import tt.interactive.ventures.ui.mainscreen.viewmodel.MainScreenViewModel

@Module
abstract class MainScreenModule {
    @Binds
    @ScreenScope
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @ScreenScope
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun provideViewModel(viewModel: MainScreenViewModel): ViewModel
}