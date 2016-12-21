package baishuai.github.io.keddit.feature.news

import baishuai.github.io.keddit.data.wrapper.RedditChildren
import baishuai.github.io.keddit.data.wrapper.RedditNewsWrapper
import baishuai.github.io.keddit.repo.RedditRepo
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Bai Shuai on 16/12/18.
 */
class NewsPresenter @Inject constructor(private val service: RedditRepo) {


    fun getNews(after: String, limit: Int = 10): Observable<RedditNewsWrapper> {
        return Observable.create { subscriber ->
            val callResponse = service.getNews(after, limit)
            val response = callResponse.execute()
            if (response.isSuccessful) {
                val dataResponse = response.body().data

                val news = dataResponse.children.map(RedditChildren::data)
                val newsWrapper = RedditNewsWrapper(
                        dataResponse.before ?: "",
                        dataResponse.after ?: "",
                        news
                )
                subscriber.onNext(newsWrapper)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}