package baishuai.github.io.keddit.data.wrapper

import baishuai.github.io.keddit.data.model.RedditNewsItem

/**
 * Created by Bai Shuai on 16/12/20.
 */
class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildren>,
        val after: String?,
        val before: String?
)


class RedditChildren(val data: RedditNewsItem)