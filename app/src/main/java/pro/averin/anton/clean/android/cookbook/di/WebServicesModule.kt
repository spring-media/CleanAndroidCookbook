package pro.averin.anton.clean.android.cookbook.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pro.averin.anton.clean.android.cookbook.BuildConfig
import pro.averin.anton.clean.android.cookbook.data.WebServicesConfig
import pro.averin.anton.clean.android.cookbook.data.flickr.FlickrAPIService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class WebServicesModule {
    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.addInterceptor {
            val url = it.request()
                    .url().newBuilder()
                    .addQueryParameter(WebServicesConfig.FLICKR_API_KEY_PARAM, BuildConfig.FLICKR_API_KEY)
                    .addQueryParameter(WebServicesConfig.FLICKR_FORMAT_KEY_PARAM, "json")
                    .addQueryParameter(WebServicesConfig.FLICKR_NO_CALLBACK_PARAM, "1")
                    .build()

            val request = it.request().newBuilder()
                    .url(url)
                    .build()

            it.proceed(request)
        }

        if (BuildConfig.DEBUG) {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addNetworkInterceptor(logger)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun getFlickrApi(client: OkHttpClient): FlickrAPIService {
        return createClient(FlickrAPIService::class.java, WebServicesConfig.FLICKR_API_ENDPOINT, client)
    }

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