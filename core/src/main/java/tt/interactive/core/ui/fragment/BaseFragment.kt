package tt.interactive.core.ui.fragment

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseFragment<VM>(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected val viewModel: VM by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDagger()
        (viewModel as? LifecycleObserver)?.let { lifecycle.addObserver(it) }
        observeUiState()
        observeEvent()
    }

    protected abstract fun initDagger()
    protected abstract suspend fun observeUiState(viewModel: VM)
    protected abstract suspend fun observeEvent(viewModel: VM)

    private fun observeUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeUiState(viewModel)
            }
        }
    }

    private fun observeEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                observeEvent(viewModel)
            }
        }
    }

}