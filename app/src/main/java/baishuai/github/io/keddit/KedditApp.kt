package baishuai.github.io.keddit

import android.app.Application
import baishuai.github.io.keddit.injection.AppComponent
import baishuai.github.io.keddit.injection.AppModule
import baishuai.github.io.keddit.injection.DaggerAppComponent
import timber.log.Timber

/**
 * Created by Bai Shuai on 16/12/21.
 */
class KedditApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}