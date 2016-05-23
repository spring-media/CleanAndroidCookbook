package pro.averin.anton.clean.android.cookbook.di

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import pro.averin.anton.clean.android.cookbook.BaseContext
import javax.inject.Singleton

@Module
class SystemServicesModule(private val baseContext: BaseContext) {

    @Provides
    @Singleton
    fun locationManager(): LocationManager {
        return baseContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Provides
    @Singleton
    fun sharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(baseContext)
    }


}