package baishuai.github.io.keddit.feature.news

import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper

/**
 * Created by Bai Shuai on 17/1/15.
 */
interface NewsView {
    fun showNews(news: RedditNewsWrapper)
    fun showError(err: Throwable)
}