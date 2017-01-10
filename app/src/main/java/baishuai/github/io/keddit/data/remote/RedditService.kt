package baishuai.github.io.keddit.data.remote

import baishuai.github.io.keddit.data.wrapper.RedditNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Bai Shuai on 16/12/20.
 */


interface RedditService {
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String): Call<RedditNewsResponse>
}