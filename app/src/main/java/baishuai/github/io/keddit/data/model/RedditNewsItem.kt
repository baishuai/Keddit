package baishuai.github.io.keddit.data.model

import baishuai.github.io.keddit.feature.common.ViewType
import baishuai.github.io.keddit.feature.news.AdapterConstants
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
) : ViewType, PaperParcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelRedditNewsItem.CREATOR
    }

    override fun getViewType() = AdapterConstants.NEWS
}