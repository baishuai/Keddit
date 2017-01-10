package baishuai.github.io.keddit.data.repo

import baishuai.github.io.keddit.data.remote.RedditService
import baishuai.github.io.keddit.data.wrapper.RedditNewsResponse
import dagger.Module
import dagger.Provides
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Bai Shuai on 16/12/20.
 */

@Module
class RedditModule {

    @Provides
    fun provideRedditService(retrofit: Retrofit): RedditService {
        return retrofit.create(RedditService::class.java)
    }
}

class RedditRepo @Inject constructor(private val redditApi: RedditService) {

    fun getNews(after: String, limit: Int): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit.toString())
    }
}


