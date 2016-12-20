package baishuai.github.io.keddit.data.model

import baishuai.github.io.keddit.feture.common.ViewType
import baishuai.github.io.keddit.feture.news.AdapterConstants

/**
 * Created by Bai Shuai on 16/12/18.
 */
data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}