package baishuai.github.io.keddit.repo

import baishuai.github.io.keddit.data.remote.RedditApi
import baishuai.github.io.keddit.data.wrapper.RedditNewsResponse
import retrofit2.Call
import javax.inject.Inject

/**
 * Created by Bai Shuai on 16/12/20.
 */
class RedditRepo @Inject constructor(private val redditApi: RedditApi) {

    fun getNews(after: String, limit: Int): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit.toString())
    }
}