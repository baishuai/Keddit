package baishuai.github.io.keddit.data.model

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by Bai Shuai on 16/12/18.
 */
@PaperParcel
data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : PaperParcelable {

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR = PaperParcelRedditNewsItem.CREATOR
    }

}