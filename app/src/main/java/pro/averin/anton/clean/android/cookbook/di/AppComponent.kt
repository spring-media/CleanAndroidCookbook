package pro.averin.anton.clean.android.cookbook.di

import com.tbruyelle.rxpermissions.RxPermissions
import dagger.Component
import pro.averin.anton.clean.android.cookbook.BaseContext
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        WebServicesModule::class,
        SystemServicesModule::class
))
interface AppComponent {
    //here we list things that are of Singleton-scope and should be accessible by other scopes
    fun baseContext(): BaseContext

    fun rxPermissions(): RxPermissions
}