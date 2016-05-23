package pro.averin.anton.clean.android.cookbook

import android.support.multidex.MultiDexApplication
import pro.averin.anton.clean.android.cookbook.di.*

class BaseContext : MultiDexApplication() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .webServicesModule(WebServicesModule())
                .systemServicesModule(SystemServicesModule(this))
                .build()

    }
}