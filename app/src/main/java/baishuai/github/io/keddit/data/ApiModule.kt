package baishuai.github.io.keddit.data

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Bai Shuai on 16/12/21.
 * API Module for Dagger2
 */
@Module
class ApiModule {

    companion object {
        const val API_HTTP_CLIENT = "API_HTTP_CLIENT"
    }

    @Named(API_HTTP_CLIENT)
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        val builder = Moshi.Builder()
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(@Named(API_HTTP_CLIENT) httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        val builder = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .client(httpClient)
                //.addCallAdapterFactory(RxJavaCallAdapterFactory)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
        return builder.build()
    }

}