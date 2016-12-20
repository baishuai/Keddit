package baishuai.github.io.keddit.repo

import baishuai.github.io.keddit.data.remote.RedditApi
import baishuai.github.io.keddit.data.wrapper.RedditNewsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Bai Shuai on 16/12/20.
 */
class RedditRepo() {

    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        redditApi = retrofit.create(RedditApi::class.java)
    }

    fun getNews(after: String, limit: Int): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit.toString())
    }
}