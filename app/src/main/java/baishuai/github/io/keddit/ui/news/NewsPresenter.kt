package baishuai.github.io.keddit.ui.news

import baishuai.github.io.keddit.data.model.RedditNewsItem
import io.reactivex.Single

/**
 * Created by Android on 16/12/18.
 */
class NewsPresenter {


    fun getNews(): Single<List<RedditNewsItem>> {
        return Single.create { subscriber ->
            val news = (1..10).map {
                RedditNewsItem(
                        "author$it",
                        "Title $it",
                        it,
                        1457207701L - it * 200,
                        "http://lorempixel.com/200/200/technics/$it",
                        "url"
                )
            }
            subscriber.onSuccess(news)
        }
    }

}