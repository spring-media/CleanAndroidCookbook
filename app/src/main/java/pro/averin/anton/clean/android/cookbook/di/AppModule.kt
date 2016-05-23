package pro.averin.anton.clean.android.cookbook.di

import android.content.res.Resources
import com.google.gson.Gson
import com.tbruyelle.rxpermissions.RxPermissions
import dagger.Module
import dagger.Provides
import pro.averin.anton.clean.android.cookbook.BaseContext
import javax.inject.Singleton

@Module
class AppModule(private val baseContext: BaseContext) {

    @Provides
    @Singleton
    fun baseContext(): BaseContext {
        return baseContext
    }

    @Provides
    @Singleton
    fun resources(): Resources {
        return baseContext.resources
    }

    @Provides
    @Singleton
    fun rxPermissions(): RxPermissions {
        return RxPermissions.getInstance(baseContext)
    }

    @Provides
    @Singleton
    fun getGson(): Gson {
        return Gson()
    }
}