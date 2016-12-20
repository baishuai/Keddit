package baishuai.github.io.keddit.ui.news

import baishuai.github.io.keddit.data.model.RedditNewsItem
import baishuai.github.io.keddit.data.wrapper.RedditChildren
import baishuai.github.io.keddit.service.RedditService
import io.reactivex.Observable

/**
 * Created by Bai Shuai on 16/12/18.
 */
class NewsPresenter(private val service: RedditService = RedditService()) {


    fun getNews(limit: Int = 10): Observable<List<RedditNewsItem>> {
        return Observable.create { subscriber ->
            val callResponse = service.getNews("", limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val news = response.body().data.children.map(RedditChildren::data)
                subscriber.onNext(news)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}