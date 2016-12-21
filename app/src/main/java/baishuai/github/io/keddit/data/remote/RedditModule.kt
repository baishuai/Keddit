package baishuai.github.io.keddit.data.remote

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by Bai Shuai on 16/12/21.
 */
@Module
class RedditModule {

    @Provides
    fun provideRedditApi(retrofit: Retrofit): RedditApi {
        return retrofit.create(RedditApi::class.java)
    }
}