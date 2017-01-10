package baishuai.github.io.keddit.data.wrapper

import baishuai.github.io.keddit.data.model.RedditNewsItem
import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by Bai Shuai on 16/12/20.
 */
data class RedditNewsResponse(val data: RedditDataResponse)

data class RedditDataResponse(
        val children: List<RedditChildren>,
        val after: String?,
        val before: String?
)

data class RedditChildren(val data: RedditNewsItem)

@PaperParcel
data class RedditNewsWrapper(
        val before: String,
        val after: String,
        val news: List<RedditNewsItem>
) : PaperParcelable {
    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = PaperParcelRedditNewsWrapper.CREATOR
    }
}