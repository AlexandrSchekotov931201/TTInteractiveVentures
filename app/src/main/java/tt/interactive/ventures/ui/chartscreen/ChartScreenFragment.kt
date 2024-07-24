package tt.interactive.ventures.ui.chartscreen

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import by.kirich1409.viewbindingdelegate.viewBinding
import tt.interactive.core.ui.fragment.BaseFragment
import tt.interactive.ventures.App
import tt.interactive.ventures.R
import tt.interactive.ventures.databinding.ChartScreenBinding
import tt.interactive.ventures.ui.chartscreen.adapter.PointsItemDecoration
import tt.interactive.ventures.ui.chartscreen.adapter.PointsAdapter
import tt.interactive.ventures.ui.chartscreen.model.ChartScreenEvent
import tt.interactive.ventures.ui.chartscreen.model.ChartScreenUiState
import tt.interactive.ventures.ui.chartscreen.viewmodel.ChartScreenViewModel

class ChartScreenFragment : BaseFragment<ChartScreenViewModel>(R.layout.chart_screen) {

    private val binding: ChartScreenBinding by viewBinding(ChartScreenBinding::bind)
    private val pointsAdapter: PointsAdapter by lazy { PointsAdapter() }

    override fun initDagger() {
        (activity?.application as? App)?.getApplicationComponent()
            ?.chartScreenSubComponent()
            ?.create()
            ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding.rvPoints) {
            adapter = pointsAdapter
            addItemDecoration(PointsItemDecoration(requireContext()))
        }
        with(binding.lcChart) {
            setPinchZoom(false)
            setBackgroundColor(Color.TRANSPARENT)
            setGridBackgroundColor(context.getColor(R.color.white))
            setNoDataTextColor(context.getColor(R.color.black))
            isDragEnabled = true
            setScaleEnabled(true)
            setDrawGridBackground(true)
            setTouchEnabled(true)
        }
        binding.btnSave.setOnClickListener {
            viewModel.emitEvent(ChartScreenEvent.SaveChart)
        }
    }

    override suspend fun observeUiState(viewModel: ChartScreenViewModel) {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is ChartScreenUiState.InitState -> {}
                is ChartScreenUiState.ShowData -> {
                    pointsAdapter.submitList(uiState.data.points)
                    binding.lcChart.data = uiState.data.lineData
                    binding.lcChart.invalidate()
                }
            }
        }
    }

    override suspend fun observeEvent(viewModel: ChartScreenViewModel) {
        viewModel.event.collect { effect ->
            when (effect) {
                is ChartScreenEvent.SaveChart -> {
                    saveChartToMediaStore(binding.lcChart.chartBitmap, requireContext())
                }
            }
        }
    }

    private fun saveChartToMediaStore(bitmap: Bitmap, context: Context) {
        val resolver = context.contentResolver
        val values = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME,
                getString(R.string.chart_screen_sava_chart_default_file_name).format(bitmap.hashCode())
            )
            put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }

        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        uri?.let { imageUri ->
            try {
                resolver.openOutputStream(imageUri)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    Toast.makeText(
                        context,
                        getString(R.string.chart_screen_sava_chart_success_text),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    context,
                    getString(R.string.chart_screen_sava_chart_error_text).format(e.message),
                    Toast.LENGTH_LONG
                ).show()
            }
        } ?: run {
            Toast.makeText(
                context,
                getString(R.string.chart_screen_sava_chart_error_create_filte_text),
                Toast.LENGTH_LONG
            ).show()
        }
    }

}