package tt.interactive.ventures.ui.mainscreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import tt.interactive.core.ui.fragment.BaseFragment
import tt.interactive.utils.addLifecycleAwareTextWatcher
import tt.interactive.ventures.App
import tt.interactive.ventures.R
import tt.interactive.ventures.databinding.MainScreenBinding
import tt.interactive.ventures.ui.mainscreen.model.MainScreenEvent
import tt.interactive.ventures.ui.mainscreen.model.MainScreenUiState
import tt.interactive.ventures.ui.mainscreen.viewmodel.MainScreenViewModel

class MainScreenFragment : BaseFragment<MainScreenViewModel>(R.layout.main_screen) {

    private val binding: MainScreenBinding by viewBinding(MainScreenBinding::bind)

    override fun initDagger() {
        (activity?.application as? App)?.getApplicationComponent()
            ?.mainScreenSubComponent()
            ?.create()
            ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            viewModel.emitEvent(MainScreenEvent.NextButtonClick)
        }
        binding.etCount.addLifecycleAwareTextWatcher(viewLifecycleOwner) {
            viewModel.emitEvent(
                MainScreenEvent.ChangedInputState(
                    it.toString().toIntOrNull() ?: 0
                )
            )
        }
    }

    override suspend fun observeUiState(viewModel: MainScreenViewModel) {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is MainScreenUiState.InitState -> {
                    binding.btnNext.isEnabled = false
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.tvError.text = ""
                    binding.btnNext.text = getString(R.string.main_screen_next_button_text)
                }

                is MainScreenUiState.ShowLoading -> {
                    binding.btnNext.isEnabled = false
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = true
                    binding.tvError.text = ""
                    binding.btnNext.text = ""
                }

                is MainScreenUiState.ReadyToNext -> {
                    binding.btnNext.isEnabled = true
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.tvError.text = ""
                    binding.btnNext.text = getString(R.string.main_screen_next_button_text)
                }

                is MainScreenUiState.NotReadyToNext -> {
                    binding.btnNext.isEnabled = false
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.tvError.text = ""
                    binding.btnNext.text = getString(R.string.main_screen_next_button_text)
                }

                is MainScreenUiState.ShowError -> {
                    binding.btnNext.isEnabled = true
                    binding.tvError.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.tvError.text = getString(uiState.text)
                    binding.btnNext.text = getString(R.string.main_screen_next_button_text)
                }
            }
        }
    }

    override suspend fun observeEvent(viewModel: MainScreenViewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                is MainScreenEvent.ShowChartScreen -> {
                    binding.btnNext.isEnabled = true
                    binding.tvError.isVisible = false
                    binding.progressBar.isVisible = false
                    binding.tvError.text = ""
                    binding.btnNext.text = getString(R.string.main_screen_next_button_text)
                    findNavController().navigate(R.id.action_mainScreenFragment_to_chartScreenFragment)
                }

                else -> {}
            }
        }
    }

}