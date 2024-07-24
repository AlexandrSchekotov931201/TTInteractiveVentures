package tt.interactive.ventures

import android.app.Application
import tt.interactive.ventures.di.ApplicationComponent
import tt.interactive.ventures.di.DaggerApplicationComponent

class App : Application() {

    private var applicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }

    fun getApplicationComponent(): ApplicationComponent? = applicationComponent

}