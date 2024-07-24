package tt.interactive.ventures.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tt.interactive.ventures.App
import tt.interactive.ventures.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as? App)?.getApplicationComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}