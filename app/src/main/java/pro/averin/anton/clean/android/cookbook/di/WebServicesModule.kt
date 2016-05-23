package pro.averin.anton.clean.android.cookbook.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class WebServicesModule {
    @Provides
    @Singleton
    fun getOkHttpClient(resources: Resources): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
        //add some interceptors and stuff if needed
        return clientBuilder.build()
    }

    //example how to inject Retrofit API
//    @Provides
//    @Singleton
//    fun getFooApi(client: OkHttpClient): FooPxApiService {
//        return createClient(FooPxApiService::class.java, WebServicesConfig.FOOPX_API_URL, client)
//    }

    private fun <T> createClient(type: Class<T>, baseUrl: String, client: OkHttpClient): T {

        val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()

        return retrofit.create(type)
    }
}