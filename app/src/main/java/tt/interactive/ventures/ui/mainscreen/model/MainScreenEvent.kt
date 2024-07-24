package tt.interactive.ventures.ui.mainscreen.model

sealed class MainScreenEvent {
    object NextButtonClick : MainScreenEvent()
    class ChangedInputState(val number: Int) : MainScreenEvent()
    object ShowChartScreen : MainScreenEvent()
}