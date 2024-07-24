package tt.interactive.utils

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.widget.EditText
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


fun EditText.addLifecycleAwareTextWatcher(
    lifecycleOwner: LifecycleOwner,
    afterTextChanged: (Editable?) -> Unit
) {
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // not used
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // not used
        }

        override fun afterTextChanged(s: Editable?) {
            afterTextChanged(s)
        }
    }

    addTextChangedListener(textWatcher)

    lifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            removeTextChangedListener(textWatcher)
        }
    })
}

fun Context.dpToPx(dp: Int): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    )
}